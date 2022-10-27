<template>
  <b-container>
    <b-row>
      <b-col flex>
        <p>인기질문</p>
        <b-button>질문 등록</b-button>
      </b-col>
    </b-row>
    <b-row>
      <!-- 질문 컴포넌트-->
    </b-row>
  </b-container>
</template>

<script setup>
// Composition API
import { useBoardStore } from "@/stores/board";

const store = useBoardStore();
store.increment();
</script>

<style scoped></style>
