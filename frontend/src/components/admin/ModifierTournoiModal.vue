<script setup>
import { ref, defineProps, computed } from "vue";

import { Tournois } from "@/stores/user/tournoi"

const tournoiStore = Tournois();


const show = ref(false);
const props = defineProps({tournoi:Object});
const id = props.tournoi.id;
const nom = ref(props.tournoi.nom);
const dateDebutTournoi = ref(props.tournoi.dateDebutTournoi);
const dateFinInscription = ref(props.tournoi.dateFinInscription);
const dateDebutPoule = ref(props.tournoi.dateDebutPoule);
const dateFinPoule = ref(props.tournoi.dateFinPoule);
const dateDebutTableau = ref(props.tournoi.dateDebutTableau);
const dateFinTournoi = ref(props.tournoi.dateFinTournoi);

const errors = computed(() => { 
  return tournoiStore.errors; 
})


 function validateFields() {
  if(nom.value === "" || dateDebutTournoi.value === "   "){
    return false;
  }
}


async function store() {
    tournoiStore.errors=""
    if(validateFields() === false){
        tournoiStore.errors = "Les champs nom et la date de debut du tournoi sont requis";
    }    
    else{
        var data ={"id":id,"nom":nom.value, "dateDebutTournoi":dateDebutTournoi.value}
        if(dateFinInscription.value !="") data.dateFinInscription = dateFinInscription.value
        if(dateDebutPoule.value !="") data.dateDebutPoule = dateDebutPoule.value
        if(dateFinPoule.value !="") data.dateFinPoule = dateFinPoule.value
        if(dateDebutTableau.value !="") data.dateDebutTableau = dateDebutTableau.value
        if(dateFinTournoi.value !="") data.dateFinTournoi = dateFinTournoi.value

        await tournoiStore.update(data)  
    }

    if(tournoiStore.errors == ""){
        toggleModal();
    }
 
}

function toggleModal() {
  show.value = !show.value;
}




</script>

<template>

    <i class="fa-thin fa-pen text-dark-brown cursor-pointer font-bold" @click="toggleModal" title="Modifier dates tournoi"></i>

  <div
    v-if="show"
    id="popup"
    class="z-50 fixed w-full flex justify-center inset-0 bg-transparent"
  >
    <div
      @click="toggleModal()"
      class="w-full h-full bg-black bg-opacity-40 absolute inset-0"
    ></div>
    <div class="flex items-center justify-center h-full w-full ">
      <div
        class=" bg-white text-white rounded-lg shadow fixed h-auto w-auto px-4 md:px-20 md:py-10"
      >
      <i class="fa-regular fa-xmark fixed absolute right-5 top-5 text-black text-xl font-bold cursor-pointer" @click="toggleModal()"></i>
        
        <div class="">
          <p class="text-xl text-dark-brown font-bold">Modifier  tournoi {{ tournoi.id }}</p>

            <form @submit.prevent="store" class="flex flex-col items-center justify-center space-y-5">

                <div class="flex flex-col text-red-300 text-center tracking-wider">
                    <p 
                        class="text-sm md:text-md font-normal py-2"
                        v-if="errors"
                    >
                        {{ errors }}
                    </p>
                </div>
          

              <div class="mt-2 w-full text-dark-brown flex flex-col">
                  <span class="text-sm">Nom </span>

                  <input
                      v-model="nom"
                      aria-label="Entrer nom"
                      role="input"
                      type="text"
                      placeholder="abcd *"
                      class="tracking-wider shadow poppins bg-white border border-dark-brown rounded-lg focus:outline-none text-sm font-medium leading-none py-3 w-full pl-3 mt-2"
                  />
              </div>

              <div class="flex flex-row items-end justify-between space-x-5 w-full">
                <div class="mt-2 w-1/2 text-dark-brown flex flex-col">
                    <span class="text-sm">début du tournoi </span>
                    <input
                        v-model="dateDebutTournoi"
                        aria-label="membre 1"
                        role="input"
                        type="date"
                        placeholder="Membre 1 *"
                        class="tracking-wider shadow poppins bg-white border border-dark-brown rounded-lg focus:outline-none text-sm font-medium leading-none py-3 w-full pl-3 mt-2"
                    />
                </div>

                <div class="mt-2 w-1/2 text-dark-brown flex flex-col">
                    <span class="text-sm">fin d'inscription équipes </span>
                    <input
                        v-model="dateFinInscription"
                        aria-label="dateFinInscription 2"
                        role="input"
                        type="date"
                        placeholder="Membre 2 *"
                        class="tracking-wider shadow poppins bg-white border border-dark-brown rounded-lg focus:outline-none text-sm font-medium leading-none py-3 w-full pl-3 mt-2"
                    />
                </div>
              </div>


              <div class="flex flex-row items-end justify-between space-x-5 justify-center w-full">
                <div class="mt-2 w-1/2 text-dark-brown flex flex-col">
                    <span class="text-sm">début des phases de poules </span>

                    <input
                        v-model="dateDebutPoule"
                        aria-label="membre 3"
                        role="input"
                        type="date"
                        placeholder="Membre 3"
                        class="tracking-wider shadow poppins bg-white border border-dark-brown rounded-lg focus:outline-none text-sm font-medium leading-none py-3 w-full pl-3 mt-2"
                    />
                </div>

                <div class="mt-2 w-1/2 text-dark-brown flex flex-col">
                    <span class="text-sm">fin des phases de poules </span>

                    <input
                        v-model="dateFinPoule"
                        aria-label="membre 4"
                        role="input"
                        type="date"
                        placeholder="Membre 4"
                        class="tracking-wider  shadow-xl poppins bg-white border  border-dark-brown rounded-lg focus:outline-none text-sm font-medium leading-none py-3 w-full pl-3 mt-2"
                    />
                </div>
              </div>


              <div class="flex flex-row items-end justify-between space-x-5 justify-center  w-full">
                <div class="mt-2 w-1/2 text-dark-brown flex flex-col">
                    <span class="text-sm">début des phases de tableaux </span>

                    <input
                        v-model="dateDebutTableau"
                        aria-label="membre 4"
                        role="input"
                        type="date"
                        placeholder="Membre 4"
                        class="tracking-wider  shadow-xl poppins bg-white border  border-dark-brown rounded-lg focus:outline-none text-sm font-medium leading-none py-3 w-full pl-3 mt-2"
                    />
                </div>


                <div class="mt-2 w-1/2 text-dark-brown flex flex-col">
                    <span class="text-sm">fin du tournoi </span>

                    <input
                        v-model="dateFinTournoi"
                        aria-label="membre 4"
                        role="input"
                        type="date"
                        placeholder="Membre 4"
                        class="tracking-wider  shadow-xl poppins bg-white border  border-dark-brown rounded-lg focus:outline-none text-sm font-medium leading-none py-3 w-full pl-3 mt-2"
                    />
                </div>
              </div>
          
              <div class="flex items-center justify-center w-full mt-6 mb-2">
                  <button
                      type="submit"
                      aria-label="add user"
                      role="button"
                      class="focus:ring-2 focus:ring-offset-2 focus:ring-clear-brown focus:outline-none px-6 py-3 bg-clear-brown hover:bg-opacity-80 shadow rounded-2xl md:text-md text-sm text-white w-full font-bold"
                  >
                      Modifier
                  </button>
              </div>
            </form>
        </div>
      </div>
    </div>
  </div>
</template>