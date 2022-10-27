<template>
  <b-container fluid>
    <b-row>
      <b-col class="footer">
        <img src="@/assets/logo.png" alt="" />
        <div>
          <ul>
            <li>koflowa</li>
            <li>질문</li>
            <li>도움</li>
          </ul>
        </div>
        <div>
          <ul>
            <li>Team</li>
            <li>팀원소개</li>
          </ul>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script setup></script>

<style scoped>
.footer {
  background-color: #232629;
  color: white;
  display: flex;
  justify-content: space-around;
  align-items: flex-start;
  padding-top: 1rem;
  height: 10rem;
}

img {
  width: 5rem;
}

ul li {
  list-style-type: none;
  margin: 0.5rem;
}

ul li:first-child {
  font-size: 1.1rem;
  font-weight: bold;
}
</style>
