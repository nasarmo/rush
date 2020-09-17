import Vue from "vue";
import Router from "vue-router";
import MainPage from "./components/mainPage.vue";
import JsonCSV from 'vue-json-csv';

Vue.use(Router);
Vue.component('downloadCsv', JsonCSV);

export default new Router({
  routes: [
    {
      path: "/",
      name: "mainPage",
      component: MainPage
    },
     {
       path: '/index.html',
       name: 'mainPage',
       component: MainPage
     }
  ]
});