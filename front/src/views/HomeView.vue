<template>
  <div>HomeView</div>
  <div>{{ count }}</div>
</template>

<script setup>
// Composition API
import { useBoardStore } from "@/stores/board";
import { computed } from "vue";

const store = useBoardStore();
const count = computed(() => store.count);
store.increment();
</script>

<style scoped></style>
