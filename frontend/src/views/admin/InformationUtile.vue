<script setup>
    import InfoCardVue from '../../components/InfoCard.vue';
    import InformationListeView from '../../components/admin/InformationList.vue';
    import { useRoute, useRouter, RouterLink } from "vue-router";
    import { ref, computed } from 'vue'
    const route = useRoute();
    const router = useRouter()
    const year = computed(() => {
        return route.params.rang
    })

    var infoCardDatas = {
        "infos": { 'image': 'information.png', 'number': "31", 'text': "Informations", 'color': "#3A0000" },
        "emplacements": { 'image': 'localisation.png', 'number': "30", 'text': "Emplacements", 'color': "#195937" }
    };

  

    var poule_button = computed(() => {
        return {
            'button_info': { 'link': "/admin/poules/", 'text': "Poules" }, 'actif': "Poules", 'is_fully_rounded': true
        }
    });

    var infoList = [
        { "id": 1, "numero": "A1", "name": "dzsfessdezf", "contenu": "Tatat","importance": 2},
        { "id": 2, "numero": "A1", "name": "parppp", "contenu": "Tatat", "importance": 2},
        { "id": 3, "numero": "A1", "name": "sxzdsx ", "contenu": "dzsxcc edzsxw", "importance": 2},
        { "id": 4, "numero": "A1", "name": "parppp", "contenu": "Tatat", "importance": 2},
        { "id": 5, "numero": "A1", "name": "parppp", "contenu": "Tatat e dz dqq dq","importance": 4},
    ]

</script>

<template>
    <main>

        <div class="py-10 snap-x">

            <div class="grid grid-cols-2 md:grid-cols-4 gap-y-8 gap-x-16 justify-items-stretch mb-16">
                <InfoCardVue v-bind="infoCardDatas.infos" />
                <InfoCardVue v-bind="infoCardDatas.emplacements" />
            </div>

            <div class="w-56 justify-items-stretch">
                <p class="my-5 text-dark-brown text-xl md:text-2xl font-bold">Informations</p>
                <div class="justify-between text-white bg-green-700 text-xl p-2 rounded-xl gap-4">
                    <RouterLink to="/" class="relative flex flex-row items-center text-white-600 pr-6">
                        <i class="fa-solid fa-plus justify-center items-center ml-4"></i>
                        <span class="ml-2 text-sm tracking-wide truncate">Nouvelle Information</span>
                    </RouterLink>
                </div>
            </div>

            <div class="max-w-5xl object-left m-0">
                <InformationListeView class="" :data="infoList" />
            </div>

            <div class="w-56 justify-items-stretch">
                <p class="my-5 text-dark-brown text-xl md:text-2xl font-bold">Emplacements</p>
                <div class="justify-between text-white bg-green-700 text-xl p-2 rounded-xl gap-4">
                    <RouterLink to="/" class="relative flex flex-row items-center text-white-600 pr-6">
                        <i class="fa-solid fa-plus justify-center items-center ml-4"></i>
                        <span to="/" class="ml-2 text-sm tracking-wide truncate" @click="toggleModal" title="nouveau">Nouvel emplacement</span>
                    </RouterLink>
                </div>
            </div>

            <div class="max-w-5xl object-left m-0">
                <InformationListeView class="" :data="infoList" />
            </div>

        </div>
    </main>
</template>