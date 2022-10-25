<template>
  <div class="sidebar">
    <ul>
      <router-link to="/"><div>홈</div></router-link>
      <router-link to="/ask"><div>질문</div></router-link>
      <router-link to="/tag"><div>태그</div></router-link>
      <router-link to="/user"><div>사용자</div></router-link>
      <router-link to="/codereview"><div>코드 리뷰</div></router-link>
      <li>코톡</li>
    </ul>
  </div>
</template>

<script setup></script>

<style scoped>
.sidebar ul {
  list-style-type: none;
}

.sidebar li {
  margin-top: 1.5rem;
}

.sidebar li:last-child {
  margin-bottom: 1.5rem;
}

.sidebar {
}
</style>
