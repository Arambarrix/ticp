<script setup>
import { ref, computed } from "vue";
import { Teams } from "@/stores/user/team"

const teamStore = Teams();

const nom = ref("");
const membre1 = ref("");
const membre2 = ref("");
const membre3 = ref("");
const membre4 = ref("");

const errors = computed(() => { 
  return teamStore.errors; 
})

function validateFields() {
  if(nom.value === "" || membre1.value === "" || membre2.value === ""){
    return false;
  }
}


async function store() {
    teamStore.errors=""
    if(validateFields() === false){
        teamStore.errors = "Les champs nom, membre 1 et membre2 sont requis";
    }    
    else{
        var membres =[membre1.value, membre2.value]
        if(membre3.value !="") membres.push(membre3.value)
        if(membre4.value !="") membres.push(membre4.value)

        await teamStore.store({
        "nom":nom.value,
        "membres":membres,
        })  
    }
    if(errors == ""){
        teamStore.errors=""
    }
}




</script>
<template>
    <div class="flex flex-col items-center justify-center h-full w-full pt-20">
        <p class="text-md">Informations de l'Equipe</p>
            
        <form @submit.prevent="store" class="flex flex-col items-center justify-center">

            <div class="flex flex-col text-red-300 text-center tracking-wider">
                <p 
                    class="text-sm md:text-md font-normal py-2"
                    v-if="errors"
                >
                    {{ errors }}
                </p>
            </div>
        

            <div class="mt-2 w-full text-dark-brown flex flex-col">
                <input
                    v-model="nom"
                    aria-label="Entrer nom"
                    role="input"
                    type="text"
                    placeholder="Nom équipe *"
                    class="tracking-wider shadow poppins bg-white border border-dark-brown rounded-lg focus:outline-none text-sm font-medium leading-none py-3 w-full pl-3 mt-2"
                />
            </div>

            <div class="mt-2 w-full text-dark-brown flex flex-col">
                <input
                    v-model="membre1"
                    aria-label="membre 1"
                    role="input"
                    type="text"
                    placeholder="Membre 1 *"
                    class="tracking-wider shadow poppins bg-white border border-dark-brown rounded-lg focus:outline-none text-sm font-medium leading-none py-3 w-full pl-3 mt-2"
                />
            </div>

            <div class="mt-2 w-full text-dark-brown flex flex-col">
                <input
                    v-model="membre2"
                    aria-label="membre 2"
                    role="input"
                    type="text"
                    placeholder="Membre 2 *"
                    class="tracking-wider shadow poppins bg-white border border-dark-brown rounded-lg focus:outline-none text-sm font-medium leading-none py-3 w-full pl-3 mt-2"
                />
            </div>

            <div class="mt-2 w-full text-dark-brown flex flex-col">
                <input
                    v-model="membre3"
                    aria-label="membre 3"
                    role="input"
                    type="text"
                    placeholder="Membre 3"
                    class="tracking-wider shadow poppins bg-white border border-dark-brown rounded-lg focus:outline-none text-sm font-medium leading-none py-3 w-full pl-3 mt-2"
                />
            </div>

            <div class="mt-2 w-full text-dark-brown flex flex-col">
                <input
                    v-model="membre4"
                    aria-label="membre 4"
                    role="input"
                    type="text"
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