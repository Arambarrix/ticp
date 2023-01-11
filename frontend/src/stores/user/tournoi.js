import { defineStore } from "pinia";
import axios from 'axios'
import {Constants} from "../constants";

export const Tournois = defineStore("tournois", {
  state: () => ({
    tournois:[],
    current_tournoi: {},
    is_tournoi_launched: Boolean(localStorage.getItem('is_tournoi_launched')) || false,
    is_registration_ended: Boolean(localStorage.getItem('is_registration_ended')) || false,
    errors:"",
    success:"",
    tableaux_length:0,
    equipes_length:0,
    poules_length:0,
    vainqueurs:[]
  }),
  getters: {
    getCurrentTournoi: (state) => state.current_tournoi,
    getTournois: (state) => state.tournois,
    getVainqueurs: (state) => state.vainqueurs,
    isTournoiLaunched: (state) => state.is_tournoi_launched,
    isRegistrationEnded: (state) => state.is_registration_ended,
    getTableauxLength: (state) => state.tableaux_length,
    getPoulesLength: (state) => state.poules_length,
    getEquipesLength: (state) => state.equipes_length,

  },
  actions: {
    
    async getTournoiInfo(year=new Date().getFullYear()){

        const constants = Constants();
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
                })
                .catch(function (error) {
                  console.log(error);
                });
    },

    async getAll(){

        const constants = Constants();
        await axios.get(constants.APIURI + "tournoi/")
                  .then((data) => {
                      if(data.data.code ==200){
                        this.tournois =  data.data.data
                      }
                      else{
                        this.errors =  data.data.errors
                        this.tournois =  []
                      }
                  })
                  .catch(function (error) {
                    console.log(error);
                  });
                  console.log(this.tournois)
    },

    async getVainqueursByYear(year=new Date().getFullYear()){

      const constants = Constants();
      await axios.get(constants.APIURI + "tournoi/"+year+"/vainqueurs")
                .then((data) => {
                    if(data.data.code ==200){
                      this.vainqueurs =  data.data.data

                    }
                    else{
                      this.errors =  data.data.errors
                      this.vainqueurs =  []
                    }
                })
                .catch(function (error) {
                  console.log(error);
                });
  },
    async updateTournoiInfo(){
      if(this.current_tournoi.id){
        this.tableaux_length = this.current_tournoi.tableaux.length
        this.poules_length = this.current_tournoi.poules.length
        this.equipes_length = this.current_tournoi.equipes.length

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
