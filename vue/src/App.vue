<template>
    <div id="app">
        <router-view/>
    </div>
</template>

<style>
::-webkit-scrollbar {
    padding-left: 2px;
    width: 5px;
    height: 5px;
    background-color: #f5f5f5;
}

::-webkit-scrollbar-thumb {
    border-radius: 5px;
    background-color: rgb(230, 230, 230);
}

::-webkit-scrollbar-track {
    border-radius: 5px;
    background-color: #f5f5f5;
}
</style>