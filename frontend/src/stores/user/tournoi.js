import { defineStore } from "pinia";
import axios from 'axios'
import {Constants} from "../constants";

export const Tournois = defineStore("tournois", {
  state: () => ({
    tournois:[],
    current_tournoi: {},
    is_tournoi_launched: Boolean(localStorage.getItem('is_tournoi_launched')) || false,
    is_registration_ended: Boolean(localStorage.getItem('is_registration_ended')) || false,
    errors:[],
    success:[]
  }),
  getters: {
    getCurrentTournoi: (state) => state.current_tournoi,
    isTournoiLaunched: (state) => state.is_tournoi_launched,
    isRegistrationEnded: (state) => state.is_registration_ended,
  },
  actions: {
    
    async getActualTournoiInfo(){

        const constants = Constants();
        var year = new Date().getFullYear()
        console.log(year)

        await axios.get(constants.APIURI + "tournoi/"+year)
                  .then((data) => {
                    if(data.data.code ==200){
                      this.current_tournoi =  data.data.data
                    }
                    else{
                      this.errors =  data.data.errors
                      this.current_tournoi =  {}

                    }
                    this.updateTournoiInfo()
                    console.log(data.data)
                })
                .catch(function (error) {
                  console.log(error);
                });
    },

    async getAll(){

        const constants = Constants();
        console.log("dd")

        await axios.get(constants.APIURI + "tournoi/")
                  .then((data) => {
                      if(data.data.code ==200){
                        this.tournois =  data.data.data

                      }
                      else{
                        this.errors =  data.data.errors
                        this.tournois =  []
                      }
                      console.log(data.data)
                  })
                  .catch(function (error) {
                    console.log(error);
                  });
    },

    async updateTournoiInfo(){
      console.log(this.current_tournoi)
      if(this.current_tournoi.id){

        const date = new Date();
        const start = new Date(this.current_tournoi.dateDebutTournoi);

        if(date >= start){
          this.is_tournoi_launched = true
        }
        else{
          this.is_tournoi_launched = false
        }

        if(this.current_tournoi.dateFinInscription ){
          if( date >= start && date <= new Date(this.current_tournoi.dateFinInscription)){
            this.is_registration_ended = false
          }
          else this.is_registration_ended = true
        }
        else{
          this.is_registration_ended = false
        }

      }
      else{
        this.is_tournoi_launched=false
        this.is_registration_ended=false
      }
      localStorage.setItem("is_tournoi_launched", this.is_tournoi_launched);
      localStorage.setItem("is_registration_ended", this.is_registration_ended);
    },
  },

 


  
});
