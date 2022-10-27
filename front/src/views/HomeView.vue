<template>
  <b-container>
    <b-row>
      <b-col class="title">
        <p>인기질문</p>
        <b-button variant="dark">질문 등록</b-button>
      </b-col>
    </b-row>
    <hr />
    <b-row> </b-row>
  </b-container>
</template>

<script setup>
// Composition API
import { useBoardStore } from "@/stores/board";

const store = useBoardStore();
store.increment();
</script>

<style scoped>
.title {
  margin-top: 2rem;
  display: flex;
  justify-content: space-between;
  text-align: center;
  align-items: center;
}

.title p {
  font-size: 2rem;
  font-weight: bold;
}
</style>
