<script setup>
    import InfoCardVue from './InfoCard.vue';
    import MatchListTableVue from './tableaux/MatchList.vue';
    import TeamListTableVue from './tableaux/TeamList.vue';
    import PaginationVue from './Pagination.vue';

    import { computed, onMounted } from "vue";
    import { Tournois } from "@/stores/user/tournoi"

    const props = defineProps({'poules':Array});
    const tournoiStore = Tournois();

    tournoiStore.getActualTournoiInfo()

    const tournoi_tableaux = computed(()=>tournoiStore.getTableaux);
    const tournoi_equipes = computed(()=>tournoiStore.getEquipes);


    var infoCardDatas={
        "equipe":{'image':'user.png', 'number': tournoi_equipes.value.length, 'text':"Equipes Inscrites", 'color':"#1B2A5A"},
        "poule":{'image':'group.png', 'number':props.poules.length, 'text':"Poules Générées", 'color':"#195937"},
        "tableau":{'image':'network.png', 'number':tournoi_tableaux.value.length, 'text':"Tableaux crées", 'color':"#00253A"}
    }   


    
</script>
<template>
    <div class="py-10 ">

        <div class="grid grid-cols-2 md:grid-cols-3 gap-y-8 gap-x-16 justify-items-stretch mb-8">
            <InfoCardVue v-bind="infoCardDatas.equipe"/>
            <InfoCardVue v-bind="infoCardDatas.poule"/>
            <InfoCardVue v-bind="infoCardDatas.tableau"/>
        </div>

        <div class="my-8" v-for="poule in props.poules">
            <p class="my-5 text-dark-brown text-lg md:text-2xl text-center font-bold">{{ poule.info.nom }}</p>
            <div class="flex flex-col md:flex-row justify-between">
                <MatchListTableVue class="w-full md:w-1/2" :data="poule.info.listMatchs" type="poule"/>
                <TeamListTableVue class="w-full md:w-1/2" :classements="poule.classements"/>
            </div>
        </div>

        

        <!--PaginationVue/-->

    </div>
   
</template>