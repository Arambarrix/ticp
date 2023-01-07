import { defineStore } from "pinia";
import axios from 'axios'


export const Constants = defineStore("constants", {
  state: () => ({
    APIURI: "http://localhost:8081/api/v1/",
    year: new Date().getFullYear(),
  }),
  getters: {},
  actions: {
    
  },
});
