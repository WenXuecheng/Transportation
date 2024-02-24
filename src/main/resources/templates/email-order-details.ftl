<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <style>
    @page {
      margin: 0;
    }
  </style>
</head>
<body>
<div class="header">
  <div style="padding: 10px;padding-bottom: 0px;">
    <p style="text-indent: 2em; margin-bottom: 10px;">Dear User ${nickname}, Details of your order are as follows:</p>
    <p class="code-text">Your name: ${nickname}</p>
    <p class="code-text">Order Number: ${OrderName}</p>
    <p class="code-text">Receiving adress: ${ReceiveAdress}</p>
    <p class="code-text">Desired date of issue: ${DateOut}</p>
    <p class="code-text">Number of goods: ${DeliveryNum}</p>
    <p class="code-text">Recipient's name: ${Name}</p>
    <p class="code-text">Recipient's Phone: ${Phone}</p>
    <p class="code-text">Breakdown of goods: ${Details}</p>
    <p class="code-text">Remarks: ${Remark}</p>

    <p>If you choose to pay online, please send the parcel to our site, we will weigh it and you will receive a payment email, after you have paid, your order will be confirmed. You can check the status of your order in your account.</p>
    <p>If you choose to pay in-store, please bring your parcel to our site, we will weigh it and you will receive a payment email and your order will be confirmed after you have paid. You can check the status of your order in your account.</p>
    <div class="footer">
    </div>
  </div>
</div>
</body>
</html>

<style lang="css">
  body {
    margin: 0px;
    padding: 0px;
    font: 100% SimSun, Microsoft YaHei, Times New Roman, Verdana, Arial, Helvetica, sans-serif;
    color: #000;
  }

  .header {
    height: auto;
    width: 820px;
    min-width: 820px;
    margin: 0 auto;
    margin-top: 20px;
    border: 1px solid #eee;
  }

  .code-text {
    text-align: center;
    font-family: Times New Roman;
    font-size: 22px;
    color: #C60024;
    padding: 20px 0px;
    margin-bottom: 10px;
    font-weight: bold;
    background: #ebebeb;
  }

  .footer {
    margin: 0 auto;
    z-index: 111;
    width: 800px;
    margin-top: 30px;
    border-top: 1px solid #DA251D;
  }
</style>

