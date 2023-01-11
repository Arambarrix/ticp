<script setup> 
    import { RouterLink } from "vue-router";
    import { useAuthStore } from "@/stores/auth"
    import { computed } from "vue";

    const authStore = useAuthStore();


    async function logout() {
        authStore.errors=""
    
        await authStore.logout()  
    
    }
</script>

<template>
    
    <!-- Header -->
    <div class="fixed w-full flex items-center h-14 text-white z-10 bg-white">
        <div class="flex items-center justify-start md:justify-center w-14 md:w-64 h-14 bg-blue-800 border-none">
          <span class="font-semibold text sm">    TICP TOURNOI</span> 
        </div>
        <div class="flex flex-row justify-between items-center h-14 header-right px-8">

            <div class="flex items-center space-x-6 text-sm text-white">
                <RouterLink to="/admin/creer_tournoi" class="bg-blue-900  py-1 px-3 hover:border-b-2  hover:border-gray-800 poppins"
                    >Créer un tournoi
                </RouterLink>

            </div>
        </div>
        <div class="flex flex-row justify-end items-center h-14 fixed right-8">
            <button @click="logout"  class="text-sm  hover:border-b-2 text-gray-800 hover:border-gray-800 hover:text-blue-500 poppins"
                > Déconnexion <i class="fa-light fa-right-from-bracket"></i>
            </button>
        </div>
      </div>

  </template>