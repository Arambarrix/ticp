import { defineStore } from "pinia";
import axios from 'axios'
import {Constants} from "../constants";

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
      console.log(result)
      if(result.data.code ==200){
        var team =  data.data.data
        membres.forEach(nom => {
          
        });

        this.getAllByYear(constants.year)
      }
      else{
        this.errors =  result.data.errors
      }
      console.log(data.data)
               
    },


  },
});
