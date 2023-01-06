<script setup>
  import { Tournois } from "@/stores/user/tournoi"
  import { computed } from "vue";
  import { useRouter, } from "vue-router";

  const router = useRouter()

  const tournoiStore = Tournois();

  tournoiStore.getActualTournoiInfo()


  const is_tournoi_launched = computed(()=>tournoiStore.isTournoiLaunched );
  const is_registration_ended = computed(()=>tournoiStore.isRegistrationEnded );

  if(is_tournoi_launched.value && is_registration_ended.value) {
    router.push({
      name: 'poules',
    })
  }
  else{
    router.push({
      name: 'equipes',
    })
  }

</script>

<template>
</template>
