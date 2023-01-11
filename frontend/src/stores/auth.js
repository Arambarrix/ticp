import { defineStore } from "pinia";
import { Constants } from "@/stores/constants";
import router from "@/router";
import axios from 'axios'
//import jwt_decode from "jwt-decode";
//import Cookies from 'universal-cookie';
//const cookies = new Cookies();

export const useAuthStore = defineStore({
  id: "auth",
  state: () => ({
    //access: cookies.get("access") || "",
    is_admin: Boolean(localStorage.getItem('is_admin')) || false,
    errors: "",
  }),
  getters: {
  },
  actions: {
    
    async login(userInfos) {

      const constants = Constants();
      let result = await axios.put(constants.APIURI + "admin/login", 
        userInfos
      );
      if(result.data.code ==200){
        localStorage.setItem("is_admin", true);
        localStorage.setItem("username", userInfos.username);

        this.is_admin=true;
        router.push("/admin/accueil/"+constants.year);
      }
      else{
        this.errors =  result.data.errors
      }
      
    },

    async logout() {

      const constants = Constants();
      let result = await axios.put(constants.APIURI + "admin/logout", 
        {"username":localStorage.getItem('username')}
      );

      if(result.data.code ==200){
        localStorage.setItem("is_admin", false);
        this.is_admin=false;
        router.push("/")
      }
      else{
        alert(result.data.errors)
      }

      
    },
  }
});