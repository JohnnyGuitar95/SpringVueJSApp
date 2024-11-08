<template>
  <main>
    <Navbar />
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h1 class="text-center">Справочник профессий</h1>
          <a href="/professionsApi/add" class="btn btn-primary">Добавить профессию</a>
          <table class="table table-striped">
            <thead>
            <tr>
              <th scope="col">Id</th>
              <th scope="col">Название</th>
              <th scope="col">Примечание</th>
              <th scope="col">Действия</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="profession in professions" :key="profession.id">
              <th scope="row">{{ profession.id }}</th>
              <td>{{ profession.name }}</td>
              <td>{{ profession.note }}</td>
              <td>
                <a class="btn btn-primary" :href="`/professionsApi/edit/${profession.id}`">Изменить</a>
                <button class="btn btn-danger mx-2" @click="deleteProfession(profession.id)">Удалить</button>
              </td>
            </tr>
            <tr v-if="!professions.length">
              <td colspan="4" class="text-center">Нет доступных профессий</td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
import Navbar from '../../components/Navbar.vue';

export default {
  name: 'ViewProfessions',
  components: {
    Navbar
  },
  data() {
    return {
      professions: [],
      message: ''
    };
  },

  beforeMount() {
    this.getProfessions();
  },

  methods: {
    getProfessions() {
      fetch('http://localhost:7000/professionsApi/professions')
          .then(res => {
            if (!res.ok) {
              throw new Error('Ошибка при получении данных о профессиях');
            }
            return res.json();
          })
          .then(data => {
            this.professions = data;
            console.log(data);
          })
          .catch(error => {
            console.error('Error fetching professions:', error);
            this.message = 'Не удалось загрузить профессии.'; // Сообщение об ошибке
          });
    },

    deleteProfession(id) {
      if (confirm('Вы уверены, что хотите удалить эту профессию?')) {
        fetch(`http://localhost:7000/professionsApi/profession/${id}`, {
          method: 'DELETE'
        })
            .then(response => {
              if (!response.ok) {
                throw new Error('Ошибка при удалении профессии');
              }
              this.getProfessions();
              this.message = 'Профессия успешно удалена!';
            })
            .catch(error => {
              console.error('Error deleting profession:', error);
              this.message = 'Не удалось удалить профессию.';
            });
      }
    }
  }
}
</script>