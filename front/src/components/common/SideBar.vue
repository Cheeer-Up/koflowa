<template>
  <div class="sidebar">
    <ul>
      <router-link to="/"><li>홈</li></router-link>
      <router-link to="/ask"><li>질문</li></router-link>
      <router-link to="/tag"><li>태그</li></router-link>
      <router-link to="/user"><li>사용자</li></router-link>
      <router-link to="/codereview"><li>코드 리뷰</li></router-link>
      <router-link to="/talk"><li>코톡</li></router-link>
    </ul>
  </div>
</template>

<script setup></script>

<style scoped>
.sidebar ul {
  list-style-type: none;
}

.sidebar ul a {
  text-decoration-line: none;
  text-decoration: none;
}

.router-link-exact-active li {
  border-right: solid 4px #5f8bff;
}

.sidebar li {
  margin-top: 1rem;
  padding: 0.5rem 0.5rem;
  color: black;
}

.sidebar li:hover {
  background-color: #ececec;
  font-weight: bold;
}
</style>
