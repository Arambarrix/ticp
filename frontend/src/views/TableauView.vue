<script setup>
  import BannerVue from '../components/banner/Banner.vue';
    import TableauListVue from '../components/TableauList.vue';
  var is_poules_created = true;
  var actif = "Tableaux";

</script>

<template>
  <main>
    <BannerVue :is_poules_created="is_poules_created" :actif="actif"/>
    <TableauListVue v-if="is_poules_created"/>
  </main>
  
</template>