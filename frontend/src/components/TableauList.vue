<script setup>
    import MatchListTableVue from './tableaux/MatchList.vue';

    const props = defineProps({'tours':Array, can_edit:Boolean, show_cards:Boolean, rang:String});

    
</script>
<template>
    <div class="my-8" v-for="tour in tours">
        <p class="my-5 text-dark-brown text-lg md:text-2xl text-center font-bold">Tour n°{{ tour.numTour +1 }}</p>
        <MatchListTableVue class="w-full" :data="tour.matchs" type="tableau" :rang="rang" :can_edit="can_edit"/>
    </div>
</template>