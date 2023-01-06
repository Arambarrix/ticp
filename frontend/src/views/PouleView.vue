<script setup>
  import BannerVue from '../components/banner/Banner.vue';
  import PouleListVue from '../components/PouleList.vue';

  var is_poules_created = true;
  var actif = "Poules";

</script>

<template>
  <main>
    <BannerVue :actif="actif"/>
    <PouleListVue v-if="is_poules_created"/>

  </main>
  
</template>