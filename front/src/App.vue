<template>
  <Header></Header>
  <div class="container">
    <div class="row">
      <div class="col-3">
        <SideBar></SideBar>
      </div>
      <div class="col-9">
        <RouterView></RouterView>
      </div>
    </div>
  </div>
  <Footer></Footer>
</template>

<script setup>
import { RouterView } from "vue-router";
import SideBar from "./components/common/SideBar.vue";
import Footer from "./components/common/Footer.vue";
</script>

<style scoped></style>
