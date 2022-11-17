<script setup> 
import { RouterLink, RouterView, useRouter } from "vue-router";

</script>

<template>
    
    <div class="flex flex-row w-full mt-8 items-center justify-between">
      
      <div class="flex items-center space-x-6 text-sm">
          <RouterLink to="/" class="hover:border-b-2 text-gray-800 hover:border-gray-800 poppins"
          ><i class="fa-light fa-house-blank"></i> Accueil</RouterLink>

          <RouterLink to="/historique" class="hover:border-b-2 text-gray-800 hover:border-gray-800 poppins"
              > <i class="fa-light fa-clock-rotate-left"></i> Historique</RouterLink>

          <RouterLink to="/galerie" class="hover:border-b-2 text-gray-800 hover:border-gray-800 poppins"
              > <i class="fa-light fa-images"></i> Galerie</RouterLink>
          
          <RouterLink to="/admin" class="hover:border-b-2 text-gray-800 hover:border-gray-800 poppins"
              > <i class="fa-light fa-user-gear"></i> Admin </RouterLink>
      </div>

      <RouterLink to="/" class="text-sm hover:border-b-2 text-gray-800 hover:border-gray-800 poppins"
          > Déconnexion <i class="fa-light fa-right-from-bracket"></i>
      </RouterLink>
    
  </div>
  </template>