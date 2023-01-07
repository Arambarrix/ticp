<script setup>
  import { Tournois } from "@/stores/user/tournoi"
  
  const tournoiStore = Tournois();
  tournoiStore.getActualTournoiInfo()

</script>
<template>
  <div class="about">
    <h1 class="text-pink-400">This is an galerie page</h1>
  </div>
</template>

