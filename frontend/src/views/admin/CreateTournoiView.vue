<script setup>
import { ref, computed } from "vue";
import { Tournois } from "@/stores/user/tournoi"

const tournoiStore = Tournois();

const nom = ref("");
const dateDebutTournoi = ref("");
const dateFinInscription = ref("");
const dateDebutPoule = ref("");
const dateFinPoule = ref("");
const dateDebutTableau = ref("");
const dateFinTournoi = ref("");

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
        var data ={"nom":nom.value, "dateDebutTournoi":dateDebutTournoi.value}
        if(dateFinInscription.value !="") data.dateFinInscription = dateFinInscription.value
        if(dateDebutPoule.value !="") data.dateDebutPoule = dateDebutPoule.value
        if(dateFinPoule.value !="") data.dateFinPoule = dateFinPoule.value
        if(dateDebutTableau.value !="") data.dateDebutTableau = dateDebutTableau.value
        if(dateFinTournoi.value !="") data.dateFinTournoi = dateFinTournoi.value

        await tournoiStore.store(data)  
    }
    if(errors == ""){
        tournoiStore.errors=""
    }
}




</script>
<template>
    <div class="flex flex-col items-center justify-center h-full w-full pt-20">
        <p class="text-md">Informations des tournoi</p>
            
        <form @submit.prevent="store" class="flex flex-col items-center justify-center space-y-5 md:space-y-8 w-1/3">

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

            <div class="mt-2 w-full text-dark-brown flex flex-col">
                <span class="text-sm">Date de début du tournoi </span>
                <input
                    v-model="dateDebutTournoi"
                    aria-label="membre 1"
                    role="input"
                    type="date"
                    placeholder="Membre 1 *"
                    class="tracking-wider shadow poppins bg-white border border-dark-brown rounded-lg focus:outline-none text-sm font-medium leading-none py-3 w-full pl-3 mt-2"
                />
            </div>

            <div class="mt-2 w-full text-dark-brown flex flex-col">
                <span class="text-sm">Date de fin d'inscription des équipes </span>
                <input
                    v-model="dateFinInscription"
                    aria-label="dateFinInscription 2"
                    role="input"
                    type="date"
                    placeholder="Membre 2 *"
                    class="tracking-wider shadow poppins bg-white border border-dark-brown rounded-lg focus:outline-none text-sm font-medium leading-none py-3 w-full pl-3 mt-2"
                />
            </div>

            <div class="mt-2 w-full text-dark-brown flex flex-col">
                <span class="text-sm">Date de début des phases de poules </span>

                <input
                    v-model="dateDebutPoule"
                    aria-label="membre 3"
                    role="input"
                    type="date"
                    placeholder="Membre 3"
                    class="tracking-wider shadow poppins bg-white border border-dark-brown rounded-lg focus:outline-none text-sm font-medium leading-none py-3 w-full pl-3 mt-2"
                />
            </div>

            <div class="mt-2 w-full text-dark-brown flex flex-col">
                <span class="text-sm">Date de fin des phases de poules </span>

                <input
                    v-model="dateFinPoule"
                    aria-label="membre 4"
                    role="input"
                    type="date"
                    placeholder="Membre 4"
                    class="tracking-wider  shadow-xl poppins bg-white border  border-dark-brown rounded-lg focus:outline-none text-sm font-medium leading-none py-3 w-full pl-3 mt-2"
                />
            </div>


            <div class="mt-2 w-full text-dark-brown flex flex-col">
                <span class="text-sm">Date de début des phases de tableaux </span>

                <input
                    v-model="dateDebutTableau"
                    aria-label="membre 4"
                    role="input"
                    type="date"
                    placeholder="Membre 4"
                    class="tracking-wider  shadow-xl poppins bg-white border  border-dark-brown rounded-lg focus:outline-none text-sm font-medium leading-none py-3 w-full pl-3 mt-2"
                />
            </div>


            <div class="mt-2 w-full text-dark-brown flex flex-col">
                <span class="text-sm">Date de fin du tournoi </span>

                <input
                    v-model="dateFinTournoi"
                    aria-label="membre 4"
                    role="input"
                    type="date"
                    placeholder="Membre 4"
                    class="tracking-wider  shadow-xl poppins bg-white border  border-dark-brown rounded-lg focus:outline-none text-sm font-medium leading-none py-3 w-full pl-3 mt-2"
                />
            </div>

        
            <div class="flex items-center justify-center w-full mt-6 mb-2">
                <button
                    type="submit"
                    aria-label="add user"
                    role="button"
                    class="focus:ring-2 focus:ring-offset-2 focus:ring-clear-brown focus:outline-none px-6 py-3 bg-clear-brown hover:bg-opacity-80 shadow rounded-2xl md:text-md text-sm text-white w-full font-bold"
                >
                    Créer
                </button>
            </div>
        </form>
    </div>
</template>