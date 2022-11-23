import { defineStore } from "pinia";

export const Constants = defineStore("constants", {
  state: () => ({
    APIURI: "http://localhost:5000/",
  }),
  getters: {},
  actions: {},
});
