<script setup>
  import { RouterView } from 'vue-router'
  import AdminHeader from './components/templates/AdminHeader.vue'
  import UserHeader from './components/templates/UserHeader.vue'
  import AdminLeftMenu from './components/templates/AdminLeftMenu.vue'
  import { useAuthStore } from "@/stores/auth"
  import { computed } from "vue";

  const authStore = useAuthStore();


  const is_admin = computed(() => { 
    return authStore.is_admin; 
  })
</script>

<template>

  <div v-if="!is_admin"  class="min-h-screen flex flex-col flex-auto flex-shrink-0 antialiased bg-white text-black space-y-3 overflow-x-hidden">
        <UserHeader class="my-5 px-6 md:px-8 w-full"/>
        <RouterView  class="mb-5 px-6 md:px-8 w-full"/>
  </div>

  <div v-if="is_admin" class="min-h-screen flex flex-col flex-auto flex-shrink-0 antialiased bg-white text-black">
    <AdminHeader  />
    <AdminLeftMenu />
    <div class="flex flex-col mb-5 w-screen px-8 fixed left-14 md:left-64 top-14 relative overflow-y-auto overflow-x-contain">
      <RouterView class=" " />
    </div>
  </div>


</template>
