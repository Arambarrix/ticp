<script setup>
    import MatchListTableVue from './tableaux/MatchList.vue';

    const props = defineProps({'tours':Array, can_edit:Boolean, show_cards:Boolean});


    
</script>
<template>
    <div class="my-8" v-for="tour in tours">
        <p class="my-5 text-dark-brown text-lg md:text-2xl text-center font-bold">Finale</p>
        <MatchListTableVue class="w-full" :data="tours.matchs" type="tableau" :can_edit="can_edit"/>
    </div>
</template>