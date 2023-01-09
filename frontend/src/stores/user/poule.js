import { defineStore } from "pinia";
import axios from 'axios'
import {Constants} from "../constants";
import router from "@/router"

export const Poules = defineStore("poules", {
  state: () => ({
    poules:[],
    poule: {},
    errors:[],
    success:[]
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
                    console.log(data.data)
                 })
                .catch(function (error) {
                  console.log(error);
                });
    },

    async launch_creation(data){
      const constants = Constants();
      let result = await axios.put(constants.APIURI + "create-groupes/");
      if(result.data.code ==200){
        var team =  result.data.data
        data.membres.forEach(nom => {
          axios.post(constants.APIURI + "joueurs/", 
              {
                "nom":nom,
                "equipe":{
                    "id":team.id
                }
              }
          );
          
        });
        router.push("/equipes");
        //this.getAllByYear(constants.year)
      }
      else{
        this.errors =  result.data.errors
      }
               
    },


  },
});
