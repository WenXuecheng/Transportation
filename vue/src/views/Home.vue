<script>
import {defineComponent} from 'vue'
export default defineComponent({
    name: "home",
    methods: {
    },
})
</script>

<template>
    <div>
        <nav class="navbar navbar-expand-lg navbar-transparent navbar-dark bg-dark py-4">
            <div class="container">
                <a class="navbar-brand" href="/home"><strong>Aurexp</strong> посылки из китая</a>
                <button class="navbar-toggler" type="button" data-action="offcanvas-open" data-target="#navbar_main" aria-controls="navbar_main" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
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
        <main class="main">
            <section class="spotlight parallax bg-cover bg-size--cover" data-spotlight="fullscreen" style="background-image: url('/assets/images/backgrounds/img-5.jpg')">
                <span class="mask bg-tertiary alpha-7"></span>
                <div class="spotlight-holder py-lg pt-lg-xl" style="height: auto;">
                    <div class="container d-flex align-items-center no-padding">
                        <div class="col">
                            <div class="row cols-xs-space align-items-center text-center text-md-left justify-content-center">
                                <div class="col-lg-7">
                                    <div class="text-center mt-5">
                                        <h1 class="heading h1 text-white">
                                            САМАЯ БЫСТРАЯ ОФИЦИАЛЬНАЯ ДОСТАВКА
                                        </h1>
                                        <p class="lead lh-180 text-white mt-3">
                                            посылок из китайских маркетплейсов
                                        </p>
                                        <h1 class="heading h1 text-white">
                                            Taobao и Dewu
                                        </h1>
                                        <p class="lead lh-180 text-white mt-3">
                                            Всего от 12 дней и от 60 рублей за посылку
                                        </p>
                                        <div class="mt-5">
                                            <a href="/login" class="btn btn-primary mr-3">Личный кабинет</a>
                                            <a href="/register" class="btn btn-secondary">Регистрация</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section class="slice-lg">
                <div class="container">
                    <h2 class="heading h3 strong-600 text-center mb-5">
                        Зарегистрируйтесь в нашем аккаунте, и вы сможете сделать это .......
                    </h2>
                    <div class="row-wrapper">
                        <div class="row cols-xs-space cols-sm-space cols-md-space">
                            <div class="col-md-6">
                                <div class="card bg-primary">
                                    <div class="card-body py-5">
                                        <div class="d-flex align-items-start">
                                            <div class="icon icon-lg">
                                                <i class="fab fa-twitter text-white"></i>
                                            </div>
                                            <div class="icon-text">
                                                <h3 class="heading text-white h4">Просмотр статуса заказа</h3>
                                                <p class="text-white mb-0">Вы можете отслеживать статус своего заказа в режиме реального времени и видеть, где он находится в данный момент.</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="card">
                                    <div class="card-body py-5">
                                        <div class="d-flex align-items-start">
                                            <div class="icon icon-lg">
                                                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 640 512"><!--! Font Awesome Free 6.4.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M36.8 192H603.2c20.3 0 36.8-16.5 36.8-36.8c0-7.3-2.2-14.4-6.2-20.4L558.2 21.4C549.3 8 534.4 0 518.3 0H121.7c-16 0-31 8-39.9 21.4L6.2 134.7c-4 6.1-6.2 13.2-6.2 20.4C0 175.5 16.5 192 36.8 192zM64 224V384v80c0 26.5 21.5 48 48 48H336c26.5 0 48-21.5 48-48V384 224H320V384H128V224H64zm448 0V480c0 17.7 14.3 32 32 32s32-14.3 32-32V224H512z"/></svg>
                                            </div>
                                            <div class="icon-text">
                                                <h3 class="heading h4">Таобао</h3>
                                                <p class="mb-0">Мы покупаем товары на Taobao для вас.</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row cols-xs-space cols-sm-space cols-md-space">
                            <div class="col-md-6">
                                <div class="card bg-primary">
                                    <div class="card-body py-5">
                                        <div class="d-flex align-items-start">
                                            <div class="icon icon-lg">
                                                <i class="fas fa-th-large text-white"></i>
                                            </div>
                                            <div class="icon-text">
                                                <h3 class="heading text-white h4">Управление вашими заказами</h3>
                                                <p class="text-white mb-0">После отправки заказа вы можете отменить его до того, как он будет подтвержден.</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="card">
                                    <div class="card-body py-5">
                                        <div class="d-flex align-items-start">
                                            <div class="icon icon-lg">
                                                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512"><!--! Font Awesome Free 6.4.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96z"/></svg>
                                            </div>
                                            <div class="icon-text">
                                                <h3 class="heading h4">Платежи</h3>
                                                <p class="mb-0">Вы можете оплатить любым способом, лично или отправив посылку нам банковским переводом.</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row cols-xs-space cols-sm-space cols-md-space">
                            <div class="col-md-6">
                                <div class="card bg-primary">
                                    <div class="card-body py-5">
                                        <div class="d-flex align-items-start">
                                            <div class="icon icon-lg">
                                                <i class="fas fa-file-alt text-white"></i>
                                            </div>
                                            <div class="icon-text">
                                                <h3 class="heading text-white h4">Ваши вещи не будут потеряны</h3>
                                                <p class="text-white mb-0">Когда мы получаем вашу посылку, мы взвешиваем ее, чтобы сделать фотографии и обновить статус местонахождения вашей посылки, когда она прибывает в другое место.</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="card">
                                    <div class="card-body py-5">
                                        <div class="d-flex align-items-start">
                                            <div class="icon icon-lg">
                                                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512"><!--! Font Awesome Free 6.4.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M64 64C28.7 64 0 92.7 0 128V384c0 35.3 28.7 64 64 64H512c35.3 0 64-28.7 64-64V128c0-35.3-28.7-64-64-64H64zM272 192H496c8.8 0 16 7.2 16 16s-7.2 16-16 16H272c-8.8 0-16-7.2-16-16s7.2-16 16-16zM256 304c0-8.8 7.2-16 16-16H496c8.8 0 16 7.2 16 16s-7.2 16-16 16H272c-8.8 0-16-7.2-16-16zM164 152v13.9c7.5 1.2 14.6 2.9 21.1 4.7c10.7 2.8 17 13.8 14.2 24.5s-13.8 17-24.5 14.2c-11-2.9-21.6-5-31.2-5.2c-7.9-.1-16 1.8-21.5 5c-4.8 2.8-6.2 5.6-6.2 9.3c0 1.8 .1 3.5 5.3 6.7c6.3 3.8 15.5 6.7 28.3 10.5l.7 .2c11.2 3.4 25.6 7.7 37.1 15c12.9 8.1 24.3 21.3 24.6 41.6c.3 20.9-10.5 36.1-24.8 45c-7.2 4.5-15.2 7.3-23.2 9V360c0 11-9 20-20 20s-20-9-20-20V345.4c-10.3-2.2-20-5.5-28.2-8.4l0 0 0 0c-2.1-.7-4.1-1.4-6.1-2.1c-10.5-3.5-16.1-14.8-12.6-25.3s14.8-16.1 25.3-12.6c2.5 .8 4.9 1.7 7.2 2.4c13.6 4.6 24 8.1 35.1 8.5c8.6 .3 16.5-1.6 21.4-4.7c4.1-2.5 6-5.5 5.9-10.5c0-2.9-.8-5-5.9-8.2c-6.3-4-15.4-6.9-28-10.7l-1.7-.5c-10.9-3.3-24.6-7.4-35.6-14c-12.7-7.7-24.6-20.5-24.7-40.7c-.1-21.1 11.8-35.7 25.8-43.9c6.9-4.1 14.5-6.8 22.2-8.5V152c0-11 9-20 20-20s20 9 20 20z"/></svg>
                                            </div>
                                            <div class="icon-text">
                                                <h3 class="heading h4">Рубль Юань</h3>
                                                <p class="mb-0">Не беспокойтесь о нехватке юаней.</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section class="slice pt-0 sct-color-1">
                <div class="container">
                    <div class="row justify-content-center align-items-center">
                        <div class="col-lg-8 text-center">
                            <h3 class="heading h3 font-weight-400">
                                <span class="font-weight-700">Личный кабинет </span>как профессиональный инструмент.
                            </h3>
                            <div class="mt-5">
                                <a href="/register" class="btn btn-lg btn-primary px-4">
                                    Регистрация
                                </a>
                                <a href="/login" target="_blank" class="btn btn-link">Уже зарегистрировались?</a>
                            </div>
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