package com.rucn.transportation.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rucn.transportation.common.Constants;
import com.rucn.transportation.common.Result;
import com.rucn.transportation.common.RoleEnum;
import com.rucn.transportation.entity.Order;
import com.rucn.transportation.entity.User;
import com.rucn.transportation.entity.dto.OrderDto;
import com.rucn.transportation.exception.ServiceException;
import com.rucn.transportation.mapper.FilesMapper;
import com.rucn.transportation.mapper.OrderFileMapper;
import com.rucn.transportation.mapper.OrderMapper;
import com.rucn.transportation.service.IOrderFileService;
import com.rucn.transportation.service.IOrderService;
import com.rucn.transportation.service.IUserService;
import com.rucn.transportation.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 青哥哥
 * @since 2022-02-10
 */
@RestController
@RequestMapping("/order")
public class OrderController {

        @Resource
        private IOrderService orderService;
        @Resource
        private OrderMapper orderMapper;
        @Resource
        private  IUserService userService;
        @Resource
        private OrderFileMapper orderFileMapper;
        @Resource
        private IOrderFileService iOrderFileService;
        @Resource
        private FilesMapper filesMapper;
        // 生成订单
        @PostMapping
        public Result save(@RequestBody Order order) {
                User user = TokenUtils.getCurrentUser();
                Order exitOrder;
                String orderName;
                Integer a = order.getId();
                String b = order.getOrderName();
                // orderName 等于null 或者 "" 的情况继续处理
                if (order.getOrderName().equals(""))
                {
                        order.setOrderName(null);
                }
                if (order.getId() != null || order.getOrderName() != null)
                {
                        throw new ServiceException(Constants.CODE_500, "提交失败1");
                }
                try{
                        orderName = this.generateordersName();
                        exitOrder = orderMapper.getByOrdername(orderName);

                } catch (Exception e){
                        throw new ServiceException(Constants.CODE_500, "提交失败2");
                }
                if (exitOrder == null) {
                        order.setOrderName(orderName);
                        // 发送订单信息的邮件
                        try {
                                orderService.sendMailOfOrderDetails(user, order);
                        } catch (Exception e) {
                                throw new ServiceException(Constants.CODE_500, "");
                        }
                        return Result.success(orderService.saveOrUpdate(order));
                } else {
                        throw new ServiceException(Constants.CODE_500, "提交失败3");
                }
        }


        @PostMapping("/update")
        public Result update(@RequestBody Order order) {
                return Result.success(orderService.saveOrUpdate(order));
        }
        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                orderService.removeById(id);
                return Result.success();
        }

        //如果订单有变化发送邮件
        @GetMapping("/email/{id}")
        public Result sendOrderInfo(@PathVariable Integer id) {
                Order order = orderService.getById(id);
                orderService.sendOrderInfo(order);
                try{

                } catch (Exception e){
                        throw new ServiceException(Constants.CODE_500, "发送失败");
                }
                return Result.success();
        }
        //发送支付邮件
        @GetMapping("/email/pay/{id}")
        public Result sendOrderPay(@PathVariable Integer id) {
                Order order = orderService.getById(id);
                orderService.sendOrderPay(order);
                try{

                } catch (Exception e){
                        throw new ServiceException(Constants.CODE_500, "发送失败");
                }
                return Result.success();
        }
        //发送确认订单邮件
        @GetMapping("/email/ok/{id}")
        public Result sendOrderPOk(@PathVariable Integer id) {
                Order order = orderService.getById(id);
                try{
                        orderService.sendOrderOk(order);
                } catch (Exception e){
                        throw new ServiceException(Constants.CODE_500, "发送失败");
                }
                return Result.success();
        }



        @PostMapping("/del/batch")
        public Result deleteBatch(@RequestBody List<Integer> ids) {
                User user = TokenUtils.getCurrentUser();
                if (user.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                orderService.removeByIds(ids);
                return Result.success();
        }

        @GetMapping
        public Result findAll() {
                return Result.success(orderService.list());
        }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
                return Result.success(orderService.getById(id));
        }

        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String username,
                               @RequestParam String name) {
                User user = TokenUtils.getCurrentUser();
                if (user.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
                queryWrapper.like("username", username);
                queryWrapper.like("name", name);
                queryWrapper.orderByDesc("id");
                return Result.success(orderService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }
        //查找订单总数
        @GetMapping("/ordernum")
        public Result ordernum() {
                User user = TokenUtils.getCurrentUser();
                if (user.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                return Result.success(orderMapper.countOrder());
        }

        //查找订单总数
        @GetMapping("/orderturnover")
        public Result orderturnover() {
                User user = TokenUtils.getCurrentUser();
                if (user.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                return Result.success(orderMapper.countOrderTurnover());
        }


        @GetMapping("/username")
        public Result findByUsername() {
                User user = TokenUtils.getCurrentUser();
                List<Order> order = orderMapper.selectByUsername(user.getUsername());
                return Result.success(order);
        }

//删除订单
        @DeleteMapping("/ordername/{ordername}")
        public Result deleteByUsername(@PathVariable String ordername) {
                if (StrUtil.isBlank(ordername)) {
                        throw new ServiceException(Constants.CODE_501, "没有该订单");
                }
                Order order;
                try {
                        order = orderMapper.getByOrdername(ordername);
                }catch (Exception e) {
                        throw new ServiceException(Constants.CODE_500, "系统错误");
                }
                if (order.getIsConfirmed()){
                        User user=TokenUtils.getCurrentUser();
                        if(user.getRole().equals("ROLE_ADMIN") || user.getRole().equals("ROLE_SU"))
                        {
                                orderFileMapper.removeByOrderName(ordername);
                                return Result.success(orderService.removeById(order.getId()));
                        }
                        throw new ServiceException(Constants.CODE_500, "订单已确认，删除失败");
                }
                //找到相关的ulr
                orderFileMapper.removeByOrderName(ordername);
                return Result.success(orderService.removeById(order.getId()));
        }

        @GetMapping("/export")
        public void export(HttpServletResponse response) throws Exception {
                // 从数据库查询出所有的数据
                List<Order> list = orderService.list();
                // 通过工具类创建writer 写出到磁盘路径
                // 在内存操作，写出到浏览器
                ExcelWriter writer = ExcelUtil.getWriter(true);
                //自定义标题别名
                writer.addHeaderAlias("id", "id");
                writer.addHeaderAlias("orderName", "订单号");
                writer.addHeaderAlias("sendRegion", "发出地");
                writer.addHeaderAlias("receiveRegion", "收货地");
                writer.addHeaderAlias("receiveAdress", "收货地址");
                writer.addHeaderAlias("dateOut", "快递类型");
                writer.addHeaderAlias("deliveryNum", "电话");
                writer.addHeaderAlias("others", "地址");
                writer.addHeaderAlias("name", "创建时间");
                writer.addHeaderAlias("phone", "角色");
                writer.addHeaderAlias("email", "头像");
                writer.addHeaderAlias("details", "是否启用");
                writer.addHeaderAlias("remark", "备注");
                writer.addHeaderAlias("pay", "支付方式");
                writer.addHeaderAlias("username", "用户名");
                writer.addHeaderAlias("isDelete", "是否删除");
                writer.addHeaderAlias("isConfirmed", "是否确认");
                writer.addHeaderAlias("createTime", "创建时间");
                writer.addHeaderAlias("weight", "重量");
                writer.addHeaderAlias("orderStatus", "订单状态");
                writer.addHeaderAlias("rmb", "人民币");
                writer.addHeaderAlias("rubs", "卢布");

                // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
                writer.write(list, true);

                // 设置浏览器响应的格式
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
                String fileName = URLEncoder.encode("用户信息", "UTF-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

                ServletOutputStream out = response.getOutputStream();
                writer.flush(out, true);
                out.close();
                writer.close();

        }
        private String generateordersName() {
                StringBuilder sb = new StringBuilder();
                char firstChar = (char) (new Random().nextInt(26) + 'A');
                sb.append(firstChar);
                for (int i = 0; i < 5; i++) {
                        int digit = new Random().nextInt(10);
                        sb.append(digit);
                }

                return sb.toString();
        }
}

