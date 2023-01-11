<script setup> 
import { RouterLink } from "vue-router";

</script>

<template>
      <!-- Sidebar -->
      <div class="fixed flex lg:flex flex-shrink-0 flex-col top-14 left-0 w-14 hover:w-64 md:w-64 bg-blue-900 h-full text-white transition-all duration-300 border-none z-10 sidebar">
        <div class="overflow-y-auto overflow-x-hidden flex flex-col justify-between flex-grow">
          <ul class="flex flex-col py-4 space-y-1">
            <li class="px-5 hidden md:block">
              <div class="flex flex-row items-center h-8">
                <div class="text-sm font-light tracking-wide text-gray-400 uppercase"></div>
              </div>
            </li>
            <li>
              <RouterLink :to="'/admin/accueil/'+new Date().getFullYear()" class="relative flex flex-row items-center h-11 focus:outline-none hover:bg-blue-800 dark:hover:bg-gray-600 text-white-600 hover:text-white-800 border-l-4 border-transparent hover:border-blue-500 dark:hover:border-gray-800 pr-6">
                <span class="inline-flex justify-center items-center ml-4">
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"></path></svg>
                </span>
                <span class="ml-2 text-sm tracking-wide truncate">Accueil</span>
              </RouterLink>
            </li>
            <li>
                <RouterLink to="/admin/information_utile" class="relative flex flex-row items-center h-11 focus:outline-none hover:bg-blue-800 dark:hover:bg-gray-600 text-white-600 hover:text-white-800 border-l-4 border-transparent hover:border-blue-500 dark:hover:border-gray-800 pr-6">
                    <i class="fa-regular fa-circle-info justify-center items-center ml-4"></i>
                    <span class="ml-2 text-sm tracking-wide truncate">Informations Utiles</span>
                </RouterLink>
            </li>

            <li>
                <RouterLink to="/admin/photo/2022" class="relative flex flex-row items-center h-11 focus:outline-none hover:bg-blue-800 dark:hover:bg-gray-600 text-white-600 hover:text-white-800 border-l-4 border-transparent hover:border-blue-500 dark:hover:border-gray-800 pr-6">
                    <i class="fa-light fa-images ustify-center items-center ml-4"></i>
                    <span class="ml-2 text-sm tracking-wide truncate">Photos</span>
                </RouterLink>
            </li>

          </ul>
        </div>
      </div>
      <!-- ./Sidebar -->

</template>