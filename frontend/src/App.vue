<script setup>
import { RouterLink, RouterView } from 'vue-router'
import AdminHeader from './components/templates/AdminHeader.vue'
import UserHeader from './components/templates/UserHeader.vue'
import AdminLeftMenu from './components/templates/AdminLeftMenu.vue'

var is_admin = true;
</script>

<template>
  <header class="flex flex-col fixed top-0 z-50 w-screen">
    <div class="flex flex-row bg-gray-400 w-full rounded-sm pl-6 md:pl-8 md:p-3 p-2 space-x-6">
      <div class="bg-gray-100 w-full md:h-4 h-2 rounded-md"></div>
      <div class="flex flex-row space-x-2">
        <div class="bg-gray-100 md:w-4 md:h-4 h-2 w-2 rounded-full"></div>
        <div class="bg-gray-100 md:w-4 md:h-4 h-2 w-2 rounded-full"></div>
      </div>
    </div>
    <div v-if="!is_admin" class="my-5 px-6 md:px-8">
      <UserHeader/>
    </div>
 
  </header>


    <div v-if="!is_admin" class="my-5 px-6 md:px-8">
      <RouterView />
    </div>

  <div v-if="is_admin" class="flex flex-row items-start mt-8 w-full">
    <AdminLeftMenu />
    <div class="flex flex-col mb-5 mx-8 w-full">
      <AdminHeader />
      <RouterView />
    </div>
  </div>


</template>
