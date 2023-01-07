<script setup>
  import BannerVue from '../components/banner/Banner.vue';
  import TeamListVue from '../components/TeamList.vue';
</script>

<template>
  <main>
    <BannerVue :is_begin="true"/>
    <TeamListVue />

  </main>
  
</template>
