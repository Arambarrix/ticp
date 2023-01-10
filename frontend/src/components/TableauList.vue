<script setup>
    import MatchListTableVue from './tableaux/MatchList.vue';

    const props = defineProps({can_edit:Boolean});

    var matchList =[
        {"id":1,"numero":"A1", "equipeA":{"nom":"dzsfessdezf"}, "equipeB":{"nom":"Tatat"}, "scoreA":2, "scoreB":1},
        {"id":2,"numero":"A1", "equipeA":{"nom":"dzsfessdezf"}, "equipeB":{"nom":"Tatat"}},
        {"id":3,"numero":"A1", "equipeA":{"nom":"dzsfessdezf"}, "equipeB":{"nom":"Tatat"}, "scoreA":2, "scoreB":1},
        {"id":4,"numero":"A1", "equipeA":{"nom":"dzsfessdezf"}, "equipeB":{"nom":"Tatat"}},
        {"id":5,"numero":"A1", "equipeA":{"nom":"dzsfessdezf"}, "equipeB":{"nom":"Tatat"}, "scoreA":2, "scoreB":1},
    ]
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