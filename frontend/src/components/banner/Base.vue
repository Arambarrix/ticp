<script setup> 
import { RouterLink } from "vue-router";

</script>

<template>
    <div class="relative">
        <img src="../../assets/img/banner.jpg" class="max-w-full h-auto rounded-xl opacity-75" alt="banner image" />
        <div class="flex flex-col space-y-4 absolute top-2/3 left-1/2 md:left-1/4 -translate-x-1/2 -translate-y-2/3 w-full px-2 md:w-2/5"> 
            <p class="text-md md:text-3xl lg:text-4xl text-white text-dark-brown font-bold">
                <slot name="header">Bienvenue au Tournoi Annuel de Pétanque</slot>
            </p>
            <div>
                <p class="mt-2 text-xs md:text-sm ">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                </p>
                <RouterLink to="#" class="text-xs md:text-sm text-[#5A240C] underline"> Informations Utiles</RouterLink>
            </div>
            <slot name="buttons">
            </slot>
        </div>
    </div>    
</template>