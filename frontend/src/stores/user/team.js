import { defineStore } from "pinia";
import axios from 'axios'
import {Constants} from "../constants";
import router from "@/router"

export const Teams = defineStore("teams", {
  state: () => ({
    teams:[],
    team: {},
    errors:[],
    success:[]
  }),
  getters: {
    getTeams: (state) => state.teams,
  },
  actions: {
    
    async getAllByYear(year){
      const constants = Constants();
      await axios.get(constants.APIURI + "tournoi/"+year+"/equipes")
                 .then((data) => {
                    if(data.data.code ==200){
                      this.teams =  data.data.data

                    }
                    else{
                      this.errors =  data.data.errors
                      this.teams =  []

                    }
                    console.log(data.data)
                 })
                .catch(function (error) {
                  console.log(error);
                });
    },

    async store(data){
      const constants = Constants();
      let result = await axios.post(constants.APIURI + "equipes/", 
          {
            "nom":data.nom,
            "tournoi":{
                "id":constants.year
            }
          }
      );
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
