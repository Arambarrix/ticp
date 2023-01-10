import { defineStore } from "pinia";
import axios from 'axios'
import {Constants} from "../constants";
import router from "@/router"

export const Poules = defineStore("poules", {
  state: () => ({
    poules:[],
    poule: {},
    errors:"",
    success:""
  }),
  getters: {
    getPoules: (state) => state.poules,
  },
  actions: {
    
    async getAllByYear(year){
      const constants = Constants();
      await axios.get(constants.APIURI + "tournoi/"+year+"/poules")
                 .then((data) => {
                    if(data.data.code ==200){
                      this.poules =  data.data.data

                    }
                    else{
                      this.errors =  data.data.errors
                      this.poules =  []

                    }
                 })
                .catch(function (error) {
                  console.log(error);
                });
    },

    async renseigner_score(id_match, data){
      const constants = Constants();
      let result = await axios.put(constants.APIURI + "match_poules/"+id_match,
        data
      );
      if(result.data.code ==200){
        this.getAllByYear(constants.year)
      }
      else{
        this.errors =  result.data.errors
      }
    },
   

    async launch_creation(year=new Date().getFullYear()){
      const constants = Constants();
      let result = await axios.put(constants.APIURI + "tournoi/"+year+"/create-groupes/");
      if(result.data.code ==200){
        this.success="Poule créée"
        alert(this.success)

      }
      else{
        this.errors =  result.data.errors
        alert(this.errors)
      }
               
    },


  },
});
