<script setup>
    import InfoCardVue from '../../components/InfoCard.vue';
    import BannerButtonListVue from '../../components/admin/ButtonList.vue';
    import SearchBar from '../../components/admin/SearchBar.vue';
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
            'list_text_links': [{ 'text': 'Poules', 'link': "/admin/poules/" + year.value }, {
            'text': 'Tableaux', 'link': "/admin/tableaux/"+ year.value}, {
            'text': 'Equipes', 'link': "/admin/equipe/"+ year.value}], 'actif': "Poules", 'is_fully_rounded': true }
    });
</script>

<template>
    <main>
      
        <div class="py-10 ">

            <div class="grid grid-cols-2 md:grid-cols-4 gap-y-8 gap-x-16 justify-items-stretch mb-16">
                <InfoCardVue v-bind="infoCardDatas.joeur" />
                <InfoCardVue v-bind="infoCardDatas.equipe" />
                <InfoCardVue v-bind="infoCardDatas.match" />
            </div>

            <div class="w-96 ">
                <BannerButtonListVue v-bind="banner_data" />
            </div>
            

            <div class="mt-5 justify-items-stretch ">
                <span class="text-lg sm:text-xl md:text-3xl  font-normal hover:font-bold">Poules</span>
                <SearchBar class="w-" />
            </div>

        </div>
    </main>
</template>
