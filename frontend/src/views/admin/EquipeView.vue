<script setup>
    import InfoCardVue from '../../components/InfoCard.vue';
    import BannerButtonListVue from '../../components/admin/ButtonList.vue';
    import SearchBar from '../../components/admin/SearchBar.vue';
    import BoutonGenerate from '../../components/admin/ButtonGenerate.vue';
    import EquipeListTableVue from '../../components/admin/EquipeList.vue';
    import BannerVue from "../../components/admin/banner.vue";
    import { Tournois } from "@/stores/user/tournoi"
    import { useRoute, useRouter, RouterLink } from "vue-router";
    import { ref, computed } from 'vue'
    const tournoiStore = Tournois();
    const route = useRoute();
    const router = useRouter()
    const year = computed(() => {
        return route.params.rang
    })

    tournoiStore.getTournoiInfo()
    var actif = "Equipes";

    var infoCardDatas = {
        "joeur": { 'image': 'group.png', 'number': "289", 'text': "Nombre de joeurs", 'color': "#1B2A5A" },
        "equipe": { 'image': 'user.png', 'number': "55", 'text': "Equipes Inscrites", 'color': "#3A0000" },
        "match": { 'image': 'flag.png', 'number': "1452", 'text': "Matchs Termin�s", 'color': "#195937" }
    };

    var banner_data = computed(() => {
        return {
            'list_text_links': [{ 'text': 'Poules', 'link': "/admin/poules/" + year.value }, {
                'text': 'Tableaux', 'link': "/admin/tableaux/" + year.value
            }, {
                'text': 'Equipes', 'link': "/admin/equipe/" + year.value
            }], 'actif': "Equipes", 'is_fully_rounded': true
        }
    });

    var poule_button = computed(() => {
        return {
            'button_info': { 'link': "/admin/poules/", 'text': "Poules" }, 'actif': "Poules", 'is_fully_rounded': true
        }
    });

    var equipeList = [
        { "rang": 1, "numero": "A1", "equipe": "dzsfessdezf", "points": 235, "matchgagne": "02/15"},
        { "rang": 2, "numero": "A1", "equipe": "parppp", "points": 145, "matchgagne": "15/50" },
        { "rang": 3, "numero": "A1", "equipe": "sxzdsx ", "points": 235, "matchgagne": "2/15"},
        { "rang": 4, "numero": "A1", "equipe": "parppp", "points": 145, "matchgagne": "15/50"},
        { "rang": 5, "numero": "A1", "equipe": "parppp", "points": 235, "matchgagne": "32/15" },
        { "rang": 1, "numero": "A1", "equipe": "dzsfessdezf", "points": 235, "matchgagne": "02/15"},
        { "rang": 2, "numero": "A1", "equipe": "parppp", "points": 145, "matchgagne": "15/50" },
        { "rang": 3, "numero": "A1", "equipe": "sxzdsx ", "points": 235, "matchgagne": "2/15" },
        { "rang": 4, "numero": "A1", "equipe": "parppp", "points": 145, "matchgagne": "15/50" },
        { "rang": 5, "numero": "A1", "equipe": "parppp", "points": 235, "matchgagne": "32/15" },
    ]

</script>

<template>
    <main>

        <div class="py-10 ">
            <div class="gap-y-32">
                <BannerVue :year="year" base_link="/admin/equipe/" :actif="actif" />
                <p></p>
            </div>
            <div class="grid grid-cols-2 md:grid-cols-4 gap-y-8 gap-x-16 justify-items-stretch mb-16">
                <InfoCardVue v-bind="infoCardDatas.joeur" />
                <InfoCardVue v-bind="infoCardDatas.equipe" />
                <InfoCardVue v-bind="infoCardDatas.match" />
            </div>

            <div class="w-96 ">
                <BannerButtonListVue v-bind="banner_data" />
            </div>


            <div class="max-w-5xl justify-items-stretch ">
                <div class="justify-between grid grid-cols-3 grid-flow-col gap-4">
                    <p class="my-2 text-dark-brown text-xl md:text-2xl font-bold">Equipes</p>
                    <div></div>
                    <SearchBar class="col-span-1 col-end" />

                </div>
            </div>

            <div class="max-w-5xl object-left m-0">
                <EquipeListTableVue class="" :data="equipeList" />
            </div>

        </div>
    </main>
</template>