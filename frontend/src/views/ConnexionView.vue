<script setup>
import { ref, computed } from "vue";
import { useAuthStore } from "@/stores/auth"


const authStore = useAuthStore();

const username = ref("");
const password = ref("");


const errors = computed(() => { 
  return authStore.errors; 
})

function validateFields() {
  if(username.value === "" || password.value === ""){
    return false;
  }
}


async function connect() {
    authStore.errors=""
    if(validateFields() === false){
      authStore.errors = "Les champs identifiant et mot de passe sont requis";
    }    
    else{
        await authStore.login({
        "username":username.value,
        "password":password.value,
        })  
    }

    if(errors == ""){
      authStore.errors=""
    }
    
}




</script>
<template>
    <div class="flex flex-col items-center justify-center h-full w-full pt-20">
        <p class="text-md">Connexion </p>
            
        <form @submit.prevent="connect" class="flex flex-col items-center justify-center space-y-5 md:space-y-8 w-1/3">

            <div class="flex flex-col text-red-300 text-center tracking-wider">
                <p 
                    class="text-sm md:text-md font-normal py-2"
                    membre1 v-if="errors"
                >
                    {{ errors }}
                </p>
            </div>
        

            <div class="mt-2 w-full text-dark-brown flex flex-col">
                <span class="text-sm">Identifiant </span>
                <input
                    v-model="username"
                    aria-label="Entrer Identifianr"
                    role="input"
                    type="text"
                    placeholder="hola"
                    class="tracking-wider shadow poppins bg-white border border-dark-brown rounded-lg focus:outline-none text-sm font-medium leading-none py-3 w-full pl-3 mt-2"
                />
            </div>

            <div class="mt-2 w-full text-dark-brown flex flex-col">
              <span class="text-sm">Mot de Passe </span>

                <input
                    v-model="password"
                    aria-label="Mot de passe"
                    role="input"
                    type="password"
                    placeholder="********"
                    class="tracking-wider shadow poppins bg-white border border-dark-brown rounded-lg focus:outline-none text-sm font-medium leading-none py-3 w-full pl-3 mt-2"
                />
            </div>



        
            <div class="flex items-center justify-center w-full mt-6 mb-2">
                <button
                    type="submit"
                    aria-label="add user"
                    role="button"
                    class="focus:ring-2 focus:ring-offset-2 focus:ring-clear-brown focus:outline-none px-6 py-3 bg-clear-brown hover:bg-opacity-80 shadow rounded-2xl md:text-sm text-sm text-white w-full font-bold"
                >
                    Se Connecter
                </button>
            </div>
        </form>
    </div>
</template>