<script setup>
    import TeamVue from './Team.vue'
    import PaginationVue from './Pagination.vue';
    import { ref, computed } from 'vue'


    var is_order_down = ref(true)
    function change_order(){
        is_order_down.value = !is_order_down.value
    }
    const teams = computed(()=>{
        return [{'name':"BABA", 'nb_joueur':5}, {'name':"Nom Equipe", 'nb_joueur':5}, {'name':"BABA", 'nb_joueur':5},
        {'name':"Nom Equipe long", 'nb_joueur':5},{'name':"Nom Equipe très long", 'nb_joueur':5},{'name':"BABA", 'nb_joueur':5},{'name':"BABA", 'nb_joueur':5}, {'name':"BABA", 'nb_joueur':5}, {'name':"Nom Equipe", 'nb_joueur':5}, {'name':"BABA", 'nb_joueur':5},
        {'name':"Nom Equipe long", 'nb_joueur':5},{'name':"BABA", 'nb_joueur':5},{'name':"BABA", 'nb_joueur':5},{'name':"BABA", 'nb_joueur':5},{'name':"BABA", 'nb_joueur':5}]
    });
</script>
<template>
    <div class="px-20 py-10 ">

        <div class="flex flex-row justify-between">
            <div class="text-xl font-bold text-dark-brown">86 Equipes inscrites</div>
            <div class="flex flex-row space-x-5">
                <div><i class="fa-sharp fa-solid fa-sliders"></i> Trier par</div>
                <div class="flex flex-row space-x-1 font-semibold text-center"> <span>Nom Equipe</span> <i class="fa-solid" :class="is_order_down?'fa-sort-down':'fa-sort-up'" @click="change_order()"></i> </div>
            </div>
        </div>

        <div class="flex flex-col px-2 pt-10 pb-8">
            <div class="grid grid-rows-5 grid-cols-2 md:grid-cols-4 gap-2">
                <TeamVue v-for="team in teams" :name="team.name" :nb_joueur="team.nb_joueur"/>
            </div>
        </div>

        <PaginationVue/>

        
    </div>
</template>