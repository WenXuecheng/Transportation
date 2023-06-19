<script>
import {defineComponent} from 'vue'

export default defineComponent({
    name: "ServicePay"
})
</script>

<template>
    <div>
        <nav class="navbar navbar-expand-lg navbar-transparent navbar-light bg-white py-4">
            <div class="container">
                <a class="navbar-brand" href="/home"><strong>Aurexp</strong>  посылки из китая</a>
                <button class="navbar-toggler" type="button" data-action="offcanvas-open" data-target="#navbar_main" aria-controls="navbar_main" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="el-icon-arrow-down el-icon--right"></i>
                </button>
                <div class="navbar-collapse offcanvas-collapse" id="navbar_main">
                    <ul class="navbar-nav ml-auto align-items-lg-center">
                        <h6 class="dropdown-header font-weight-600 d-lg-none px-0">Меню</h6>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbar_main_dropdown_1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Доставка</a>
                            <div class="dropdown-menu" aria-labelledby="navbar_1_dropdown_1">
                                <a class="dropdown-item" href="/delivery_cn">Доставка по Китаю</a>
                                <a class="dropdown-item" href="/delivery_ru">Доставка по России</a>
                                <a class="dropdown-item" href="/delivery_condition">Условия доставки</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbar_main_dropdown_1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Услуги</a>
                            <div class="dropdown-menu" aria-labelledby="navbar_1_dropdown_1">
                                <a class="dropdown-item" href="/service">Услуги и цены</a>
                                <a class="dropdown-item" href="/service_pay">Оплата услуг</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbar_main_dropdown_1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Международная доставка</a>
                            <div class="dropdown-menu" aria-labelledby="navbar_1_dropdown_1">
                                <a class="dropdown-item" href="/marketplace">Доставка из китайских маркетплейсов</a>
                                <a class="dropdown-item" href="/customs_clearance">Таможенное оформление</a>
                                <a class="dropdown-item" href="/rules_individuals">Таможенные правила для физических лиц</a>
                                <a class="dropdown-item" href="/prohibited_goods">Товары, запрещённые к ввозу на территорию России</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/faq">FAQ</a>
                        </li>
                        <div class="dropdown-divider d-lg-none my-4"></div>
                        <li class="nav-item">
                            <a href="/login" target="_blank" class="nav-link d-lg-none">ЛИЧНЫЙ КАБИНЕТ</a>
                            <a href="/login" target="_blank" class="btn btn-sm bg-white d-none d-lg-inline-flex">ЛИЧНЫЙ КАБИНЕТ</a>
                        </li>
                        <div class="dropdown-divider d-lg-none my-4"></div>
                        <h6 class="dropdown-header font-weight-600 d-lg-none px-0">Social Media</h6>
                        <li class="nav-item">
                            <a class="nav-link nav-link-icon" href="#" target="_blank"><svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512"><!--! Font Awesome Free 6.4.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M31.4907 63.4907C0 94.9813 0 145.671 0 247.04V264.96C0 366.329 0 417.019 31.4907 448.509C62.9813 480 113.671 480 215.04 480H232.96C334.329 480 385.019 480 416.509 448.509C448 417.019 448 366.329 448 264.96V247.04C448 145.671 448 94.9813 416.509 63.4907C385.019 32 334.329 32 232.96 32H215.04C113.671 32 62.9813 32 31.4907 63.4907ZM75.6 168.267H126.747C128.427 253.76 166.133 289.973 196 297.44V168.267H244.16V242C273.653 238.827 304.64 205.227 315.093 168.267H363.253C359.313 187.435 351.46 205.583 340.186 221.579C328.913 237.574 314.461 251.071 297.733 261.227C316.41 270.499 332.907 283.63 346.132 299.751C359.357 315.873 369.01 334.618 374.453 354.747H321.44C316.555 337.262 306.614 321.61 292.865 309.754C279.117 297.899 262.173 290.368 244.16 288.107V354.747H238.373C136.267 354.747 78.0267 284.747 75.6 168.267Z"/></svg><span class="ml-2 d-lg-none">VK</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-link-icon" href="#" target="_blank"><svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512"><!--! Font Awesome Free 6.4.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M380.9 97.1C339 55.1 283.2 32 223.9 32c-122.4 0-222 99.6-222 222 0 39.1 10.2 77.3 29.6 111L0 480l117.7-30.9c32.4 17.7 68.9 27 106.1 27h.1c122.3 0 224.1-99.6 224.1-222 0-59.3-25.2-115-67.1-157zm-157 341.6c-33.2 0-65.7-8.9-94-25.7l-6.7-4-69.8 18.3L72 359.2l-4.4-7c-18.5-29.4-28.2-63.3-28.2-98.2 0-101.7 82.8-184.5 184.6-184.5 49.3 0 95.6 19.2 130.4 54.1 34.8 34.9 56.2 81.2 56.1 130.5 0 101.8-84.9 184.6-186.6 184.6zm101.2-138.2c-5.5-2.8-32.8-16.2-37.9-18-5.1-1.9-8.8-2.8-12.5 2.8-3.7 5.6-14.3 18-17.6 21.8-3.2 3.7-6.5 4.2-12 1.4-32.6-16.3-54-29.1-75.5-66-5.7-9.8 5.7-9.1 16.3-30.3 1.8-3.7.9-6.9-.5-9.7-1.4-2.8-12.5-30.1-17.1-41.2-4.5-10.8-9.1-9.3-12.5-9.5-3.2-.2-6.9-.2-10.6-.2-3.7 0-9.7 1.4-14.8 6.9-5.1 5.6-19.4 19-19.4 46.3 0 27.3 19.9 53.7 22.6 57.4 2.8 3.7 39.1 59.7 94.8 83.8 35.2 15.2 49 16.5 66.6 13.9 10.7-1.6 32.8-13.4 37.4-26.4 4.6-13 4.6-24.1 3.2-26.4-1.3-2.5-5-3.9-10.5-6.6z"/></svg><span class="ml-2 d-lg-none">Whatsapp</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-link-icon" href="#" target="_blank"><svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 496 512"><!--! Font Awesome Free 6.4.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M248,8C111.033,8,0,119.033,0,256S111.033,504,248,504,496,392.967,496,256,384.967,8,248,8ZM362.952,176.66c-3.732,39.215-19.881,134.378-28.1,178.3-3.476,18.584-10.322,24.816-16.948,25.425-14.4,1.326-25.338-9.517-39.287-18.661-21.827-14.308-34.158-23.215-55.346-37.177-24.485-16.135-8.612-25,5.342-39.5,3.652-3.793,67.107-61.51,68.335-66.746.153-.655.3-3.1-1.154-4.384s-3.59-.849-5.135-.5q-3.283.746-104.608,69.142-14.845,10.194-26.894,9.934c-8.855-.191-25.888-5.006-38.551-9.123-15.531-5.048-27.875-7.717-26.8-16.291q.84-6.7,18.45-13.7,108.446-47.248,144.628-62.3c68.872-28.647,83.183-33.623,92.511-33.789,2.052-.034,6.639.474,9.61,2.885a10.452,10.452,0,0,1,3.53,6.716A43.765,43.765,0,0,1,362.952,176.66Z"/></svg><span class="ml-2 d-lg-none">Telegram</span></a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <main>
            <section class="slice slice-xl">
                <div class="container">
                    <div class="mb-md text-center">
                        <h1 class="heading h1">Оплата услуг</h1>
                        <div class="fluid-paragraph text-left mt-4">
                            <h2 class="heading h2">Базовый тариф:</h2>
                            <p>Международная доставка осуществляется до г.Уссурийска.</p>
                            <p>Срок доставки составляет 6-10 дней (регламентный срок доставки временно увеличен).</p>
                            <p>Цена доставки 600 ₽ за 1 килограмм.</p>
                            <p>Если вес заказа составляет менее 100 грамм, то стоимость доставки округляется до 100 грамм.</p>
                            <p>Дополнительные услуги оплачиваются отдельно.</p>
                            <p>Отправка в регионы оплачивается отдельно.</p>
                            <h2 class="heading h2">Порядок оплаты услуг:</h2>
                            <p>Оплата услуг осуществляется на сайте RAKETACN.RU, после получения и обработки ваших посылок на нашем складе в Китае.</p>
                            <p>Срок доставки начинает отсчитываться с момента оплаты услуг сервиса RAKETA.</p>
                            <h2 class="heading h2">Как оплатить:</h2>
                            <p>1. Списание средств, происходит с баланса в личном кабинете;</p>
                            <p>2. Пополнить баланс можно в личном кабинете. К оплате принимаются карты VISA / MasterCard / МИР;
                                Обратите внимание, если на нашем сайте при создании заказа вы ввели неверный трек номер посылки, или в личном кабинете указали не все необходимые данные для оформления посылки, такая посылка не сможет начать оформление и это приведёт к задержке международной доставки. При указании недостоверных данных посылка не сможет пройти таможенный контроль!</p>
                        </div>
                    </div>
                </div>
            </section>
        </main>
        <footer class="pt-5 pb-3 footer  footer-dark bg-tertiary">
            <div class="container">
                <div class="row">
                    <div class="col-12 col-md-4">
                        <div class="pr-lg-5">
                            <h1 class="heading h6 text-uppercase font-weight-700 mb-3"><strong>Aurexp</strong> посылки из китая</h1>
                            <p>Предоставление профессионального, быстрого и гарантированного логистического сервиса.</p>
                        </div>
                    </div>
                    <div class="col-6 col-md">
                        <h5 class="heading h6 text-uppercase font-weight-700 mb-3">Доставка</h5>
                        <ul class="list-unstyled text-small">
                            <li><a class="text-muted" href="/delivery_cn">Доставка по Китаю</a></li>
                            <li><a class="text-muted" href="/delivery_ru">Доставка по России</a></li>
                            <li><a class="text-muted" href="/delivery_condition">Условия доставки</a></li>
                        </ul>
                    </div>
                    <div class="col-6 col-md">
                        <h5 class="heading h6 text-uppercase font-weight-700 mb-3">Услуги</h5>
                        <ul class="list-unstyled text-small">
                            <li><a class="text-muted" href="/service">Услуги и цены</a></li>
                            <li><a class="text-muted" href="/service_pay">Оплата услуг</a></li>
                            <li><a class="text-muted" href="/faq">FAQ</a></li>
                        </ul>
                    </div>
                    <div class="col-6 col-md">
                        <h5 class="heading h6 text-uppercase font-weight-700 mb-3">Международная доставка</h5>
                        <ul class="list-unstyled text-small">
                            <li><a class="text-muted" href="/marketplace">Доставка из китайских маркетплейсов</a></li>
                            <li><a class="text-muted" href="/customs_clearance">Таможенное оформление</a></li>
                            <li><a class="text-muted" href="/rules_individuals">Таможенные правила для физических лиц</a></li>
                            <li><a class="text-muted" href="/prohibited_goods">Товары, запрещённые к ввозу на территорию России</a></li>
                        </ul>
                    </div>
                </div>
                <hr>
                <div class="d-flex align-items-center">
          <span class="">
            &copy; 2023 <a href="#" class="footer-link" target="_blank">Aurexp</a>. All rights reserved.
          </span>
                    <ul class="nav ml-lg-auto">
                        <li class="nav-item">
                            <a class="nav-link nav-link-icon" href="#" target="_blank"><svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512"><!--! Font Awesome Free 6.4.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M31.4907 63.4907C0 94.9813 0 145.671 0 247.04V264.96C0 366.329 0 417.019 31.4907 448.509C62.9813 480 113.671 480 215.04 480H232.96C334.329 480 385.019 480 416.509 448.509C448 417.019 448 366.329 448 264.96V247.04C448 145.671 448 94.9813 416.509 63.4907C385.019 32 334.329 32 232.96 32H215.04C113.671 32 62.9813 32 31.4907 63.4907ZM75.6 168.267H126.747C128.427 253.76 166.133 289.973 196 297.44V168.267H244.16V242C273.653 238.827 304.64 205.227 315.093 168.267H363.253C359.313 187.435 351.46 205.583 340.186 221.579C328.913 237.574 314.461 251.071 297.733 261.227C316.41 270.499 332.907 283.63 346.132 299.751C359.357 315.873 369.01 334.618 374.453 354.747H321.44C316.555 337.262 306.614 321.61 292.865 309.754C279.117 297.899 262.173 290.368 244.16 288.107V354.747H238.373C136.267 354.747 78.0267 284.747 75.6 168.267Z"/></svg><span class="ml-2 d-lg-none">VK</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-link-icon" href="#" target="_blank"><svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512"><!--! Font Awesome Free 6.4.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M380.9 97.1C339 55.1 283.2 32 223.9 32c-122.4 0-222 99.6-222 222 0 39.1 10.2 77.3 29.6 111L0 480l117.7-30.9c32.4 17.7 68.9 27 106.1 27h.1c122.3 0 224.1-99.6 224.1-222 0-59.3-25.2-115-67.1-157zm-157 341.6c-33.2 0-65.7-8.9-94-25.7l-6.7-4-69.8 18.3L72 359.2l-4.4-7c-18.5-29.4-28.2-63.3-28.2-98.2 0-101.7 82.8-184.5 184.6-184.5 49.3 0 95.6 19.2 130.4 54.1 34.8 34.9 56.2 81.2 56.1 130.5 0 101.8-84.9 184.6-186.6 184.6zm101.2-138.2c-5.5-2.8-32.8-16.2-37.9-18-5.1-1.9-8.8-2.8-12.5 2.8-3.7 5.6-14.3 18-17.6 21.8-3.2 3.7-6.5 4.2-12 1.4-32.6-16.3-54-29.1-75.5-66-5.7-9.8 5.7-9.1 16.3-30.3 1.8-3.7.9-6.9-.5-9.7-1.4-2.8-12.5-30.1-17.1-41.2-4.5-10.8-9.1-9.3-12.5-9.5-3.2-.2-6.9-.2-10.6-.2-3.7 0-9.7 1.4-14.8 6.9-5.1 5.6-19.4 19-19.4 46.3 0 27.3 19.9 53.7 22.6 57.4 2.8 3.7 39.1 59.7 94.8 83.8 35.2 15.2 49 16.5 66.6 13.9 10.7-1.6 32.8-13.4 37.4-26.4 4.6-13 4.6-24.1 3.2-26.4-1.3-2.5-5-3.9-10.5-6.6z"/></svg><span class="ml-2 d-lg-none">Whatsapp</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-link-icon" href="#" target="_blank"><svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 496 512"><!--! Font Awesome Free 6.4.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M248,8C111.033,8,0,119.033,0,256S111.033,504,248,504,496,392.967,496,256,384.967,8,248,8ZM362.952,176.66c-3.732,39.215-19.881,134.378-28.1,178.3-3.476,18.584-10.322,24.816-16.948,25.425-14.4,1.326-25.338-9.517-39.287-18.661-21.827-14.308-34.158-23.215-55.346-37.177-24.485-16.135-8.612-25,5.342-39.5,3.652-3.793,67.107-61.51,68.335-66.746.153-.655.3-3.1-1.154-4.384s-3.59-.849-5.135-.5q-3.283.746-104.608,69.142-14.845,10.194-26.894,9.934c-8.855-.191-25.888-5.006-38.551-9.123-15.531-5.048-27.875-7.717-26.8-16.291q.84-6.7,18.45-13.7,108.446-47.248,144.628-62.3c68.872-28.647,83.183-33.623,92.511-33.789,2.052-.034,6.639.474,9.61,2.885a10.452,10.452,0,0,1,3.53,6.716A43.765,43.765,0,0,1,362.952,176.66Z"/></svg><span class="ml-2 d-lg-none">Telegram</span></a>
                        </li>
                    </ul>
                </div>
            </div>
        </footer>
    </div>
</template>

<style scoped>

</style>