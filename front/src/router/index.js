import { createRouter, createWebHistory } from "vue-router";
import authRouter from "./authRouter";
import HomeView from "../views/HomeView.vue";
import CodeReviewView from "@/views/CodeReviewView.vue";
import UserView from "@/views/UserView.vue";
import askRouter from "./askRouter";
import TagView from "@/views/Tag/TagView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    ...authRouter,
    ...askRouter,
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/tag",
      name: "tag",
      component: TagView,
    },
    {
      path: "/codereview",
      name: "codereview",
      component: CodeReviewView,
    },
    {
      path: "/user",
      name: "user",
      component: UserView,
    },
  ],
});

export default router;
