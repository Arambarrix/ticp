<script setup>
    import InfoCardVue from '../../components/InfoCard.vue';
    import BannerButtonListVue from '../../components/admin/ButtonList.vue';
    import SearchBar from '../../components/admin/SearchBar.vue';
    import BoutonGenerate from '../../components/admin/ButtonGenerate.vue';
    import MatchListTableVue from '../../components/admin/MatchList.vue';
    import { useRoute, useRouter, RouterLink } from "vue-router";
    import { ref, computed } from 'vue'
    const route = useRoute();
    const router = useRouter()
    const year = computed(() => {
        return route.params.rang
    })

    var infoCardDatas = {
        "joeur": { 'image': 'group.png', 'number': "289", 'text': "Nombre de joeurs", 'color': "#1B2A5A" },
        "equipe": { 'image': 'user.png', 'number': "55", 'text': "Equipes Inscrites", 'color': "#3A0000" },
        "match": { 'image': 'flag.png', 'number': "1452", 'text': "Matchs Terminés", 'color': "#195937" }
    };

    var banner_data = computed(() => {
        return {
            'list_text_links': [{ 'text': 'Poules', 'link': "/admin/poules/"+ year.value }, {
                'text': 'Tableaux', 'link': "/admin/tableaux/"+ year.value
            }, {
                'text': 'Equipes', 'link': "/admin/equipe/"+ year.value
            }], 'actif': "Poules", 'is_fully_rounded': true
        }
    });

    var poule_button = computed(() => {
        return {
            'button_info': { 'link': "/admin/poules/", 'text': "Poules" }, 'actif': "Poules", 'is_fully_rounded': true
        }
    });

    var matchList = [
        { "id": 1, "numero": "A1", "equipe1": "dzsfessdezf", "equipe2": "Tatat", "gagnant": "Tatat", "score1": 2, "score2": 1 },
        { "id": 1, "numero": "A1", "equipe1": "parppp", "equipe2": "Tatat", "gagnant": "Tatat" },
        { "id": 1, "numero": "A1", "equipe1": "sxzdsx ", "equipe2": "dzsxcc edzsxw", "gagnant": "c xdcxvdcx fdcx dc", "score1": 20, "score2": 56 },
        { "id": 1, "numero": "A1", "equipe1": "parppp", "equipe2": "Tatat", "gagnant": "Tatat" },
        { "id": 1, "numero": "A1", "equipe1": "parppp", "equipe2": "Tatat e dz dqq dq", "gagnant": "Tatat", "score1": 45, "score2": 20 },
    ]

</script>

<template>
    <main>

        <div class="py-10 snap-x">

            <div class="grid grid-cols-2 md:grid-cols-4 gap-y-8 gap-x-16 justify-items-stretch mb-16">
                <InfoCardVue v-bind="infoCardDatas.joeur" />
                <InfoCardVue v-bind="infoCardDatas.equipe" />
                <InfoCardVue v-bind="infoCardDatas.match" />
            </div>

            <div class="w-96 ">
                <BannerButtonListVue v-bind="banner_data" />
            </div>


            <div class="max-w-5xl justify-items-stretch ">
                <p class="my-5 text-dark-brown text-xl md:text-2xl font-bold">Poules</p>
                <div class="justify-between grid grid-cols-3 grid-flow-col gap-4">

                    <BoutonGenerate v-bind="poule_button" class="text-white bg-green-700 rounded-full" />
                    <div></div>
                    <SearchBar class="col-span-1 col-end" />

                </div>
            </div>

            <div class="max-w-5xl object-left m-0">
                <MatchListTableVue class="" :data="matchList" />
                <MatchListTableVue class="" :data="matchList" />
                <MatchListTableVue class="" :data="matchList" />
            </div>

        </div>
    </main>
</template>