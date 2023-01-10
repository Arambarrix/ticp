<script setup>
    import MatchListTableVue from './tableaux/MatchList.vue';

    import { computed } from "vue";
    import { Tournois } from "@/stores/user/tournoi";

    const props = defineProps({'tableaux':Array, can_edit:Boolean, show_cards:Boolean});

    const tournoiStore = Tournois();

    tournoiStore.getTournoiInfo()

    const tournoi_tableaux_length = computed(()=>tournoiStore.getTableauxLength);
    const tournoi_equipes_length = computed(()=>tournoiStore.getEquipesLength);
    const tournoi_poules_length = computed(()=>tournoiStore.getPoulesLength);

    var infoCardDatas= computed(()=>{
        return {
            "equipe":{'image':'user.png', 'number': tournoi_equipes_length.value, 'text':"Equipes Inscrites", 'color':"#1B2A5A"},
            "poule":{'image':'group.png', 'number':tournoi_poules_length.value, 'text':"Poules Générées", 'color':"#195937"},
            "tableau":{'image':'network.png', 'number':tournoi_tableaux_length.value, 'text':"Tableaux crées", 'color':"#00253A"}
        } 
    })

    
</script>
<template>
    <div class="my-8">
        <p class="my-5 text-dark-brown text-lg md:text-2xl text-center font-bold">Finale</p>
        <MatchListTableVue class="w-full" :data="matchList" type="tableau" :can_edit="can_edit"/>
    </div>

    <div class="my-8">
        <p class="my-5 text-dark-brown text-lg md:text-2xl text-center font-bold">Demi Finale</p>
        <MatchListTableVue class="w-full" :data="matchList" type="tableau" :can_edit="can_edit"/>
    </div>

    <div class="my-8">
        <p class="my-5 text-dark-brown text-lg md:text-2xl text-center font-bold">Quart de Finale</p>
        <MatchListTableVue class="w-full" :data="matchList" type="tableau" :can_edit="can_edit" />
    </div>

    <div class="my-8">
        <p class="my-5 text-dark-brown text-lg md:text-2xl text-center font-bold">Huitième de Finale</p>
        <MatchListTableVue class="w-full" :data="matchList" type="tableau" :can_edit="can_edit"/>
    </div>
</template>