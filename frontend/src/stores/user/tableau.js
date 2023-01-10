import { defineStore } from "pinia";
import axios from 'axios'
import {Constants} from "../constants";
import router from "@/router"

export const Tableaux = defineStore("tableaux", {
  state: () => ({
    tableaux:[],
    tableau: {},
    errors:"",
    success:[]
  }),
  getters: {
    getTableaux: (state) => state.tableaux,
    getTableau: (state) => state.tableau
  },
  actions: {
    
    async getAll(year=new Date().getFullYear()){
      const constants = Constants();
      await axios.get(constants.APIURI + "tournoi/"+year+"/tableaux")
                 .then((data) => {
                    if(data.data.code ==200){
                      this.tableaux =  data.data.data
                    }
                    else{
                      this.errors =  data.data.errors
                      this.tableaux =  []

                    }
                 })
                .catch(function (error) {
                  console.log(error);
                });
    },

    async getTab(rang, year=new Date().getFullYear()){
      const constants = Constants();
      await axios.get(constants.APIURI + "tournoi/"+year+"/tableaux/"+rang)
                 .then((data) => {
                    if(data.data.code ==200){
                      this.tableau =  data.data.data
                    }
                    else{
                      this.errors =  data.data.errors
                      this.tableau =  {}

                    }
                 })
                .catch(function (error) {
                  console.log(error);
                });
    },

    async renseigner_score(id_match, data, rang){
      const constants = Constants();
      let result = await axios.put(constants.APIURI + "match_tableaux/"+id_match,
        data
      );
      console.log(rang)
      if(result.data.code ==200){
        this.getTab(rang)
      }
      else{
        this.errors =  result.data.errors
      }
    },
   
    //admin
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
