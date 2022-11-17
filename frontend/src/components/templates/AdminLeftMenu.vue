<script setup> 
import { RouterLink, RouterView, useRouter } from "vue-router";

</script>


<template>
    <!-- component -->
<div>
      <nav class="flex flex-col bg-blue-900 w-56 h-screen px-4 tex-gray-900 border border-blue-900 items align-items-center">
        <div class="flex items-center mt-8">
            <span class="font-semibold text-white">    TICP TOURNOI</span> 
        </div>
        <div class="mt-10 mb-4 text-white">
          <div class=" flex flex-col text-center item-center">
            <div class="my-2 py-2 text-sm   text-gray-500  border-gray-300 hover:text-black   bg-blue-400  hover:font-bold  rounded-full items-center">
              <RouterLink to="/" class=" w-full poppins space-x-1 items-center"><i class="fa-light fa-house-blank"></i> <span class="text-center">Accueil</span> </RouterLink>
            </div>

            <div class="my-2 py-2 text-sm   text-gray-500  border-gray-300 hover:text-black   bg-blue-400  hover:font-bold  rounded-full items-center">
              <RouterLink to="/" class=" w-full poppins space-x-1 items-center"><i class="fa-light fa-house-blank"></i> <span class="text-center">Accueil</span> </RouterLink>
            </div>

            <div class="my-2 py-2 text-sm   text-gray-500  border-gray-300 hover:text-black   bg-blue-400  hover:font-bold  rounded-full items-center">
              <RouterLink to="/" class=" w-full poppins space-x-1 items-center"><i class="fa-light fa-house-blank"></i> <span class="text-center">Accueil</span> </RouterLink>
            </div>

            <div class="my-2 py-2 text-sm   text-gray-500  border-gray-300 hover:text-black   bg-blue-400  hover:font-bold  rounded-full items-center">
              <RouterLink to="/" class=" w-full poppins space-x-1 items-center"><i class="fa-light fa-house-blank"></i> <span class="text-center">Accueil</span> </RouterLink>
            </div>
        </div>
        </div>
      </nav>
    </div>
</template>