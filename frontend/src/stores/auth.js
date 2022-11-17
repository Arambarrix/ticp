import { defineStore } from "pinia";
import { Constants } from "@/stores/constants";
import router from "@/router";
//import jwt_decode from "jwt-decode";
//import Cookies from 'universal-cookie';
//const cookies = new Cookies();

export const useAuthStore = defineStore({
  id: "auth",
  state: () => ({
    //access: cookies.get("access") || "",
    is_admin: '',
    errors: [],
  }),
  getters: {
  },
  actions: {
    
    login(userInfos) {
        const constante = Constants();
        fetch(constante.APIURI + "login", {
          method: "POST",
          headers: {
            "content-type": "application/json; charset=UTF-8",
          },
          body: JSON.stringify(userInfos),
        })
          .then((response) => response.json())
          .then((data) => {
            if (data.message) {
              this.errors = ["Invalid email or password !"];
            } else {
              this.access = data.access_token;
              /*
              cookies.set("access", data.access_token, {
                path: "/",
                secure: true,
              });*/
              router.push("/");
            }
          });
    },
    logout() {
      router.push("/login")
    },
  }
});