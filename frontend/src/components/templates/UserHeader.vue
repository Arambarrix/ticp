<script setup> 
import { RouterLink } from "vue-router";

</script>


<template>

    <header 
        class="flex justify-between z-50 w-screen"
        >

        <RouterLink to="/" class="text sm md:text-md font-medium hover:border-b-2 text-gray-800 hover:border-gray-800 poppins"
            > TICP TOURNOI
        </RouterLink>

        <div class="flex items-center space-x-6 text-sm">
            <RouterLink to="/" class="hover:border-b-2 text-gray-800 hover:border-gray-800 poppins"
            ><i class="fa-light fa-house-blank"></i> Accueil</RouterLink>

            <RouterLink :to="'/historiques/'+new Date().getFullYear()" class="hover:border-b-2 text-gray-800 hover:border-gray-800 poppins"
                > <i class="fa-light fa-clock-rotate-left"></i> Historique</RouterLink>

            <RouterLink :to="'/galerie/'+new Date().getFullYear()" class="hover:border-b-2 text-gray-800 hover:border-gray-800 poppins"
                > <i class="fa-light fa-images"></i> Galerie</RouterLink>
            
            <RouterLink to="/connexion" class="hover:border-b-2 text-gray-800 hover:border-gray-800 poppins"
                > <i class="fa-light fa-user-gear"></i> Admin</RouterLink>
        </div>
      
    </header>
</template>