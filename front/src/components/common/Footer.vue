<template>
  <b-container class="footer" fluid>
    <b-row cols="3">
      <b-col class="logo"><img src="@/assets/logo.png" alt="" /></b-col>
      <b-col class="koflowa">
        <ul>
          <li>koflowa</li>
          <li>질문</li>
          <li>도움</li>
        </ul>
      </b-col>
      <b-col class="team">
        <ul>
          <li>Team</li>
          <li>팀원소개</li>
        </ul>
      </b-col>
    </b-row>
  </b-container>
</template>

<script setup></script>

<style scoped>
.footer {
  background-color: #232629;
  color: white;
}

.logo img {
  width: 5rem;
}

ul li {
  list-style-type: none;
}
</style>
