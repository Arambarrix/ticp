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
  },
  actions: {
    
    async getAll(year, rang){
      const constants = Constants();
      await axios.get(constants.APIURI + "tournoi/"+year+"/tableaux/"+rang)
                 .then((data) => {
                    if(data.data.code ==200){
                      this.tableaux =  data.data.data

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
