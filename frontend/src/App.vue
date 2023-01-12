<script setup>
  import { RouterView } from 'vue-router'
  import AdminHeader from './components/templates/AdminHeader.vue'
  import UserHeader from './components/templates/UserHeader.vue'
  import AdminLeftMenu from './components/templates/AdminLeftMenu.vue'
  import { useAuthStore } from "@/stores/auth"
  import { watch, computed} from 'vue'

  const authStore = useAuthStore();

  const is_admin = computed(() => { 
    return authStore.is_admin; 
  })

  watch(is_admin, isAdminChanged)

  function isAdminChanged(){
    authStore.updateIsAdmin()
  }
</script>

<template>

  <div v-if="!is_admin"  class="min-h-screen flex flex-col flex-auto flex-shrink-0 antialiased bg-white text-black space-y-3 overflow-x-hidden">
        <UserHeader class="my-5 px-6 md:px-8 w-full"/>
        <RouterView  class="mb-5 px-6 md:px-8 w-full" :key="is_admin"/>
  </div>

  <div v-if="is_admin" class="min-h-screen flex flex-col flex-auto flex-shrink-0 antialiased bg-white text-black">
    <AdminHeader  />
    <AdminLeftMenu />
    <div class="flex flex-col w-auto  mb-5 px-8  ml-14 md:ml-64 mt-14  h-full flex-grow-0 overflow-y-auto ">
      <RouterView class=" overflow-x-hidden w-full" :key="is_admin"/>
    </div>
  </div>


</template>
