<script setup>
  import BannerVue from '../components/banner/Banner.vue';
  import TeamListVue from '../components/TeamList.vue';
  var is_poules_created = true;
  var actif = "Equipes";

</script>

<template>
  <main>
    <BannerVue :is_poules_created="is_poules_created" :actif="actif"/>
    <TeamListVue v-if="is_poules_created"/>
  </main>
  
</template>